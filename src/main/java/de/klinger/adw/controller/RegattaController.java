package de.klinger.adw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Regatta;
import de.klinger.adw.service.impl.RegattaService;

@RestController
@RequestMapping("/regattas")
public class RegattaController {
	
	@Autowired
	private RegattaService regattaService;
	
	 @RequestMapping(method=RequestMethod.GET)
	  public List<Regatta> getAll() {
	    return regattaService.getAllRegattas();
	  }
	  
	  @RequestMapping(method=RequestMethod.POST)
	  public Regatta create(@RequestBody Regatta regatta) {
	    return regattaService.saveRegatta(regatta);
	  }
	  
	  @RequestMapping(method=RequestMethod.GET, value="{id}")
	  public Regatta getOne(@PathVariable String id) {
		  return regattaService.findOne(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
	  public void delete(@PathVariable String id) {
		  regattaService.delete(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.PUT, value="{id}")
	  public Regatta update(@PathVariable String id, @RequestBody Regatta regatta) {
	    Regatta update = regattaService.findOne(id);
	    update.setName(regatta.getName());
	    update.setShortName(regatta.getShortName());
	    update.setStartDate(regatta.getStartDate());
	    update.setEndDate(regatta.getEndDate());
	    update.setFinished(regatta.isFinished());
	    update.setBuoyages(regatta.getBuoyages());
	    return regattaService.save(update);
	  }
	
}
