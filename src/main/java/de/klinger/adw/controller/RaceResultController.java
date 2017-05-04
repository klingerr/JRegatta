package de.klinger.adw.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import de.klinger.adw.service.impl.ResultService;
import de.klinger.adw.service.impl.SkipperService;


@RestController
@RequestMapping("/regattas/{regattaId}/races/{raceId}/results")
public class RaceResultController {
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResultService resultService;
    @Autowired
    private SkipperService skipperService;

    @Value("${result.penaltyPoints}")
    private Integer penaltyPoints = 1;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Result> getAllByRaceId(@PathVariable Long raceId) {
    	List<Result> raceResults = new ArrayList<>();
    	boolean saveResults = true;
    	
    	for (AgeGroup ageGroup : AgeGroup.values()) {
    		List<Result> raceResultsByAgeGroup = resultService.getAllByRaceIdAndSkipperAgeGroupOrderByPlacement(raceId, ageGroup);
//    		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
    		Collections.sort(raceResultsByAgeGroup);
//    		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
    		if (!raceResultsByAgeGroup.isEmpty()) {
	    		int defaultPenaltyPoints = skipperService.countByRegattaIdAndAgeGroup(raceResultsByAgeGroup.get(0).getSkipper().getRegatta().getId(), ageGroup) + penaltyPoints;;
	    		saveResults = calculatePoints(raceResultsByAgeGroup, defaultPenaltyPoints);
    		}
    		raceResults.addAll(raceResultsByAgeGroup);
    	}
    	
        if (saveResults) {
        	resultService.saveResults(raceResults);
        }
        return raceResults;
    }

	protected boolean calculatePoints(List<Result> raceResultsByAgeGroup, int defaultPenaltyPoints) {
		int points = 0;
		boolean saveResults = false;
		for (Result result : raceResultsByAgeGroup) {
		    if (result.getPoints() == 0) {
		    	saveResults = true;

				// vorhandener Zieleinlauf
				if (isNumeric(result.getPlacement())) {
					result.setPoints(++points);
					for (Judgement judgement : Judgement.values()) {
						if (judgement.equals(result.getJudgement())) {
							if (judgement.isFullPenalty()) {
								result.setPoints(defaultPenaltyPoints);
							} else {
								int specialPenaltyPoints = points + (int)(judgement.getAdditionalFee() * points);
								if (specialPenaltyPoints > defaultPenaltyPoints) {
								}
								result.setPoints(specialPenaltyPoints);
							}
						}
					}
				} else {
					// nicht durchs Ziel gekommen
					result.setPoints(defaultPenaltyPoints);
				}
		    }
		}
		return saveResults;
	}

    @RequestMapping(method = RequestMethod.POST)
    public Result create(@RequestBody Result result) {
        return resultService.saveResult(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Result getOne(@PathVariable String id) {
        return resultService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable String id) {
        resultService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Result update(@PathVariable String id, @RequestBody Result result) {
        log.info("result: " + result);
        log.info("result.getRace().getRegatta().getId(): " + result.getRace().getRegatta());
        Result update = resultService.findOne(id);
        
        update.setPoints(result.getPoints());
        update.setJudgement(result.getJudgement());
        
        return resultService.save(update);
    }
    
    public boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    }  

}
