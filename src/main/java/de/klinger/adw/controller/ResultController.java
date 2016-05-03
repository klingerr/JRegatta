package de.klinger.adw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Result;
import de.klinger.adw.service.impl.ResultService;

@RestController
@RequestMapping("/regattas/{regattaId}/races/{raceId}/results")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	 @RequestMapping(method=RequestMethod.GET)
	  public List<Result> getAllByRaceId(@PathVariable Long raceId) {
	    return resultService.getAllByRaceId(raceId);
	  }
	  
	  @RequestMapping(method=RequestMethod.POST)
	  public Result create(@RequestBody Result result) {
	    return resultService.saveResult(result);
	  }
	  
	  @RequestMapping(method=RequestMethod.GET, value="{id}")
	  public Result getOne(@PathVariable String id) {
		  return resultService.findOne(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
	  public void delete(@PathVariable String id) {
		  resultService.delete(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.PUT, value="{id}")
	  public Result update(@PathVariable String id, @RequestBody Result result) {
	    Result update = resultService.findOne(id);
	    update.setPlacement(result.getPlacement());
	    update.setPoints(result.getPoints());
	    update.setSkipper(result.getSkipper());
	    update.setRace(result.getRace());
	    return resultService.save(update);
	  }
	
}
