package de.klinger.adw.dto;

import de.klinger.adw.domain.Skipper;
import java.util.HashMap;
import java.util.Map;

public class RegattaResultDto implements Comparable<RegattaResultDto> {
    private static final int LAST_RACE_NUMBER = 3;

    
    private Skipper skipper;
    private Map<Integer, Integer> racePoints = new HashMap<>();
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
        for (int points : racePoints.values()) {
            finalPoints += points;
        }
        return finalPoints;
    }

    @Override
    public int compareTo(RegattaResultDto o) {
        int ageGroupCompare = this.getSkipper().getAgeGroup().compareTo(o.getSkipper().getAgeGroup());
        if (ageGroupCompare == 0) {
            int finalPointsCompare = this.getFinalPoints().compareTo(o.getFinalPoints());
            if (finalPointsCompare == 0) {
                try {
                    return this.getRacePoints().get(LAST_RACE_NUMBER).compareTo(o.getRacePoints().get(LAST_RACE_NUMBER));
                } catch(Exception e) {
                    return finalPointsCompare;
                }
            } else {
                return finalPointsCompare;
            }
        }
        return ageGroupCompare;
    }

}
