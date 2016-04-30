package de.klinger.adw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Club;
import de.klinger.adw.service.impl.ClubService;

@RestController
@RequestMapping("/clubs")
public class ClubController {
	
	@Autowired
	private ClubService clubService;
	
	 @RequestMapping(method=RequestMethod.GET)
	  public List<Club> getAll() {
	    return clubService.getAllClubs();
	  }
	  
	  @RequestMapping(method=RequestMethod.POST)
	  public Club create(@RequestBody Club club) {
	    return clubService.saveClub(club);
	  }
	  
	  @RequestMapping(method=RequestMethod.GET, value="{id}")
	  public Club getOne(@PathVariable String id) {
		  return clubService.findOne(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
	  public void delete(@PathVariable String id) {
		  clubService.delete(id);
	  }
	  
	  @RequestMapping(method=RequestMethod.PUT, value="{id}")
	  public Club update(@PathVariable String id, @RequestBody Club club) {
	    Club update = clubService.findOne(id);
	    update.setName(club.getName());
	    update.setShortName(club.getShortName());
	    update.setAdress(club.getAdress());
	    return clubService.save(update);
	  }
	
}
