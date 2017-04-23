package de.klinger.adw.controller;

import de.klinger.adw.domain.AgeGroup;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Result;
import de.klinger.adw.domain.Skipper;
import de.klinger.adw.dto.RegattaResultDto;
import de.klinger.adw.service.impl.ResultService;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/regattas/{regattaId}/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RegattaResultDto> getRegattaResultsDtoByRegattaId(@PathVariable Long regattaId) {
        List<RegattaResultDto> regattaResultDtos = new ArrayList<>();
        
        List<Result> results = resultService.getAllByRaceRegattaIdOrderBySkipper(regattaId);

        setFinalPoints(results, regattaResultDtos);
        
        setFinalPlacement(regattaResultDtos);
        
        return regattaResultDtos;
    }

    private void setFinalPoints(List<Result> results, List<RegattaResultDto> regattaResultDtos) {
        Skipper oldSkipper = null;
        RegattaResultDto regattaResultDto = null;
        for (Result result : results) {
            if (!result.getSkipper().equals(oldSkipper)) {
                oldSkipper = result.getSkipper();
                regattaResultDto = new RegattaResultDto(result.getSkipper());
                regattaResultDtos.add(regattaResultDto);
            }
            regattaResultDto.getRacePoints().put(result.getRace().getNumber(), result.getPoints());
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

}
