package de.klinger.adw.controller;

import de.klinger.adw.domain.AgeGroup;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Result;
import de.klinger.adw.domain.Skipper;
import de.klinger.adw.service.impl.ResultService;
import de.klinger.adw.service.impl.SkipperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/regattas/{regattaId}/races/{raceId}/results")
public class RaceResultController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResultService resultService;
    @Autowired
    private SkipperService skipperService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Result> getAllByRaceId(@PathVariable Long raceId) {
        List<Result> raceResults = resultService.getAllByRaceIdOrderByAgeGroupAndPoints(raceId);
        log.info("raceResults: " + raceResults);
        
        int result=0;
        AgeGroup oldAgeGroup = null;
        for (Result raceResult : raceResults) {
            if (!raceResult.getSkipper().getAgeGroup().equals(oldAgeGroup)) {
                oldAgeGroup = raceResult.getSkipper().getAgeGroup();
                result = 0;
            }
            raceResult.setResult(++result);
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
        
        if (result.getSkipper() != null) {
            log.info("result.getSkipper().getSailNumber(): " + result.getSkipper().getSailNumber());
            Skipper skipper = skipperService.findOneBySailNumberAndRegattaId(result.getSkipper().getSailNumber(), 3);
            update.setSkipper(skipper);
        }
        
        update.setPlacement(result.getPlacement());
        update.setPoints(result.getPoints());
        update.setRace(result.getRace());
        return resultService.save(update);
    }

}