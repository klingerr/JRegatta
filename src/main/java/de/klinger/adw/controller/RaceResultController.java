package de.klinger.adw.controller;

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
        List<Result> raceResults = resultService.getAllByRaceIdOrderByAgeGroupAndPlacement(raceId);
        log.info("raceResults: " + raceResults);
        
        int result=0;
        int points=0;
        AgeGroup oldAgeGroup = null;
        boolean saveResults = false;
        for (Result raceResult : raceResults) {
        	System.out.println("raceResult.getSkipper(): " + raceResult.getSkipper());
        	System.out.println("raceResult.getSkipper().getAgeGroup(): " + raceResult.getSkipper().getAgeGroup());
            if (!raceResult.getSkipper().getAgeGroup().equals(oldAgeGroup)) {
                oldAgeGroup = raceResult.getSkipper().getAgeGroup();
                result = 0;
                points = 0;
            }
            
            if (raceResult.getPoints() == 0) {
            	saveResults = true;
	            raceResult.setPoints(++points);
	            if (!isNumeric(raceResult.getPlacement())) {
					raceResult.setPoints(skipperService.countByRegattaIdAndAgeGroup(raceResult.getSkipper().getRegatta().getId(), raceResult.getSkipper().getAgeGroup()) + penaltyPoints);
	            }
            }
            
            if (raceResult.getPoints() != 0) {
            	raceResult.setResult(++result);
            }

        }
        
        if (saveResults) {
        	resultService.saveResults(raceResults);
        }
        return raceResults;
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
        
//        if (result.getSkipper() != null) {
//            log.info("result.getSkipper().getSailNumber(): " + result.getSkipper().getSailNumber());
//            Skipper skipper = skipperService.findOneBySailNumberAndRegattaId(result.getSkipper().getSailNumber(), result.getSkipper().getRegatta().getId());
//            update.setSkipper(skipper);
//        }
//        
//        update.setPlacement(result.getPlacement());
        update.setPoints(result.getPoints());
//        update.setRace(result.getRace());
        return resultService.save(update);
    }
    
    public boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    }  

}
