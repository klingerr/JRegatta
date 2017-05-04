package de.klinger.adw.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.klinger.adw.domain.Judgement;
import de.klinger.adw.domain.Skipper;

public class RegattaResultDto implements Comparable<RegattaResultDto> {
	private static final int VOTE_RACE_NUMBER = 1;

	private Skipper skipper;
	private Map<Integer, Integer> racePoints = new HashMap<>();
	private Map<Integer, Judgement> raceJudgements = new HashMap<>();
	private List<Integer> pointListWithoutWorstRace = new ArrayList<>();
	private BigInteger placementSortCriteria;
	private int finalPlacement;

	public int getFinalPlacement() {
		return finalPlacement;
	}

	public void setFinalPlacement(int finalPlacement) {
		this.finalPlacement = finalPlacement;
	}

	public RegattaResultDto(Skipper skipper) {
		this.skipper = skipper;
	}

	public Skipper getSkipper() {
		return skipper;
	}

	public void setSkipper(Skipper skipper) {
		this.skipper = skipper;
	}

	public Map<Integer, Integer> getRacePoints() {
		return racePoints;
	}

	public void setRacePoints(Map<Integer, Integer> racePoints) {
		this.racePoints = racePoints;
	}

	public Integer getFinalPoints() {
		int finalPoints = 0;
		for (int points : pointListWithoutWorstRace) {
			finalPoints += points;
		}
		return finalPoints;
	}

	public List<Integer> getPointListWithoutWorstRace() {
		return pointListWithoutWorstRace;
	}

	public void setPointListWithoutWorstRace(List<Integer> pointListWithoutWorstRace) {
		this.pointListWithoutWorstRace = pointListWithoutWorstRace;
	}

	public Map<Integer, Judgement> getRaceJudgements() {
		return raceJudgements;
	}

	public void setRaceJudgements(Map<Integer, Judgement> raceJudgements) {
		this.raceJudgements = raceJudgements;
	}

	public BigInteger getPlacementSortCriteria() {
		return placementSortCriteria;
	}

	public void setPlacementSortCriteria(BigInteger placementSortCriteria) {
		this.placementSortCriteria = placementSortCriteria;
	}

	@Override
	public int compareTo(RegattaResultDto o) {
		int ageGroupCompare = this.getSkipper().getAgeGroup().compareTo(o.getSkipper().getAgeGroup());
		if (ageGroupCompare == 0) {
			return this.getPlacementSortCriteria().compareTo(o.getPlacementSortCriteria());
		}
		return ageGroupCompare;
	}

}
