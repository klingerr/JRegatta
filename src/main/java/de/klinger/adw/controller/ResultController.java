package de.klinger.adw.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.AgeGroup;
import de.klinger.adw.domain.Judgement;
import de.klinger.adw.domain.Result;
import de.klinger.adw.domain.Skipper;
import de.klinger.adw.dto.RegattaResultDto;
import de.klinger.adw.service.impl.ResultService;

@RestController
@RequestMapping("/regattas/{regattaId}/results")
public class ResultController {

	private static final String STRING_FORMAT = "%02d";

	@Value("${result.ignoreWorstRace}")
	private boolean ignoreWorstRace = true;

	@Autowired
	private ResultService resultService;

	@RequestMapping(method = RequestMethod.GET)
	public List<RegattaResultDto> getRegattaResultsDtoByRegattaId(@PathVariable Long regattaId) {
		List<RegattaResultDto> regattaResultDtos = new ArrayList<>();

		List<Result> results = resultService.getAllByRaceRegattaIdOrderBySkipper(regattaId);

		aggregateRacePointsAndJudgements(results, regattaResultDtos);

		setPointListWithoutWorstRace(regattaResultDtos);

		calculatePlacementSortCriteria(regattaResultDtos);
		
		setFinalPlacement(regattaResultDtos);

		return regattaResultDtos;
	}

	private void aggregateRacePointsAndJudgements(List<Result> results, List<RegattaResultDto> regattaResultDtos) {
		Skipper oldSkipper = null;
		RegattaResultDto regattaResultDto = null;
		for (Result result : results) {
			if (!result.getSkipper().equals(oldSkipper)) {
				oldSkipper = result.getSkipper();
				regattaResultDto = new RegattaResultDto(result.getSkipper());
				regattaResultDtos.add(regattaResultDto);
			}
			regattaResultDto.getRacePoints().put(result.getRace().getNumber(), result.getPoints());
			regattaResultDto.getRaceJudgements().put(result.getRace().getNumber(), result.getJudgement());
		}
	}

	protected void setPointListWithoutWorstRace(List<RegattaResultDto> regattaResultDtos) {
		for (RegattaResultDto regattaResultDto : regattaResultDtos) {
			List<Integer> nonDeletablePoints = new ArrayList<>();
			List<Integer> deletablePoints = new ArrayList<>();
			
			for (Map.Entry<Integer, Integer> racePoint : regattaResultDto.getRacePoints().entrySet()) {
				Integer raceNumber = racePoint.getKey();
				Judgement raceJudgement = regattaResultDto.getRaceJudgements().get(raceNumber);
				
				if (raceJudgement != null && !raceJudgement.isDeleteable()) {
					nonDeletablePoints.add(racePoint.getValue());
				} else {
					deletablePoints.add(racePoint.getValue());
				}
			}
			
//			System.out.println("nonDeletablePoints: " + nonDeletablePoints);
//			System.out.println("deletablePoints: " + deletablePoints);
			
			regattaResultDto.getPointListWithoutWorstRace().addAll(nonDeletablePoints);

			Collections.sort(deletablePoints);
			if (!deletablePoints.isEmpty()) {
				regattaResultDto.getPointListWithoutWorstRace().addAll(deletablePoints.subList(0, ignoreWorstRace ? deletablePoints.size() -1 : deletablePoints.size()));
			}
		}
	}

	private void calculatePlacementSortCriteria(List<RegattaResultDto> regattaResultDtos) {
		for (RegattaResultDto regattaResultDto : regattaResultDtos) {
			// 1. criteria: sum of points
			String placementSortCriteria = "" + regattaResultDto.getFinalPoints();
			
			// 2. criteria: first best point _with_ deleted worst race
			Collections.sort(regattaResultDto.getPointListWithoutWorstRace());
			for (int points : regattaResultDto.getPointListWithoutWorstRace()) {
				placementSortCriteria += asString(points);
			}
			
			// 3. criteria: best last race _without_ deleted worst race
			Map<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
			sortedMap.putAll(regattaResultDto.getRacePoints());
			
			for (int points : sortedMap.values()) {
				placementSortCriteria += asString(points);
			}
			
//			System.out.println("placementSortCriteria: " + placementSortCriteria);
			
			regattaResultDto.setPlacementSortCriteria(new BigInteger(placementSortCriteria));
		}
	}
	
	private void setFinalPlacement(List<RegattaResultDto> regattaResultDtos) {
		Collections.sort(regattaResultDtos);
		AgeGroup oldAgeGroup = null;
		int placement = 0;
		for (RegattaResultDto result : regattaResultDtos) {
			if (!result.getSkipper().getAgeGroup().equals(oldAgeGroup)) {
				oldAgeGroup = result.getSkipper().getAgeGroup();
				placement = 0;
			}

			result.setFinalPlacement(++placement);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Result update(@PathVariable String id, @RequestBody Result result) {
		Result update = resultService.findOne(id);
		update.setPlacement(result.getPlacement());
		update.setPoints(result.getPoints());
		return resultService.save(update);
	}


	private String asString(int number) {
		return String.format(STRING_FORMAT, number);
	}

}
