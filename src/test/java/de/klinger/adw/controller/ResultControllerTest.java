package de.klinger.adw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import de.klinger.adw.domain.Judgement;
import de.klinger.adw.domain.Skipper;
import de.klinger.adw.dto.RegattaResultDto;

public class ResultControllerTest {
	
	private ResultController resultController = new ResultController();

	@Test
	public void testSetPointListWithoutWorstRace() {
		List<RegattaResultDto> regattaResultDtos = new ArrayList<>();
		RegattaResultDto regattaResultDto = new RegattaResultDto(new Skipper());
		Map<Integer, Integer> racePoints = new HashMap<>();
		// Skipper H in example sheet
		racePoints.put(1, 2);
		racePoints.put(2, 5);
		racePoints.put(3, 13);
		racePoints.put(4, 5);
		regattaResultDto.setRacePoints(racePoints);
		
		Map<Integer, Judgement> raceJudgements = new HashMap<>();
		raceJudgements.put(1, null);
		raceJudgements.put(2, null);
		raceJudgements.put(3, Judgement.DNE);
		raceJudgements.put(4, null);
		regattaResultDto.setRaceJudgements(raceJudgements);
		
		regattaResultDtos.add(regattaResultDto);
		
		resultController.setPointListWithoutWorstRace(regattaResultDtos);
		
		Assert.assertEquals(3, regattaResultDto.getPointListWithoutWorstRace().size());
		
		int sumPoints = 0;
		for (Integer points : regattaResultDto.getPointListWithoutWorstRace()) {
			sumPoints += points;
		}
		
		Assert.assertEquals(20, sumPoints);
	}
	
	@Test
	public void testStringFormat() {
		Assert.assertEquals("04", String.format("%02d", 4));
	}

}
