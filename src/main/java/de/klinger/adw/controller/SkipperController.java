package de.klinger.adw.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.AgeGroup;
import de.klinger.adw.domain.Skipper;
import de.klinger.adw.service.impl.ClubService;
import de.klinger.adw.service.impl.SkipperService;

@RestController
@RequestMapping("/regattas/{regattaId}/skippers")
public class SkipperController {

	@Value("${ak.yearShift}")
	private int yearShift = 9;

	@Value("${ak.useFirstDayOfYear}")
    private boolean useFirstDayOfYear = true;
	
    @Autowired
    private SkipperService skipperService;
    @Autowired
    private ClubService clubService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Skipper> getAllByRegattaId(@PathVariable Long regattaId) {
        return skipperService.getAllSkippersByRegattaIdOrderBySailNumberAsc(regattaId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/races/{raceId}")
    public List<Skipper> getAvailableByRaceIdOrderBySailNumberAsc(@PathVariable Long regattaId, @PathVariable Long raceId) {
    	return skipperService.getAvailableByRaceIdOrderBySailNumberAsc(regattaId, raceId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Skipper create(@RequestBody Skipper skipper) {
        return skipperService.saveSkipper(skipper);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Skipper getOne(@PathVariable String id) {
        return skipperService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable String id) {
        skipperService.delete(id);
    }

    protected AgeGroup getAgeGroupByBirthday(Date birthDay) {
    	if (birthDay == null) {
    		return null;
    	}
    	
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - yearShift);
		if (useFirstDayOfYear) {
			cal.set(Calendar.DAY_OF_YEAR, 1);
		}
		Date dueDate = cal.getTime();

		if (birthDay.before(dueDate)) {
			return AgeGroup.AK10;
		}
		return AgeGroup.AK9;
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Skipper update(@PathVariable String id, @RequestBody Skipper skipper) {
        Skipper update = skipperService.findOne(id);
        update.setLastName(skipper.getLastName());
        update.setFirstName(skipper.getFirstName());
        update.setGender(skipper.getGender());
        update.setBirthDay(skipper.getBirthDay());
        update.setAgeGroup(getAgeGroupByBirthday(skipper.getBirthDay()));
        update.setSailNumber(skipper.getSailNumber());
        if (skipper.getClub() != null) {
        	update.setClub(clubService.findOneByShortName(skipper.getClub().getShortName()));
        }
        update.setLateRegistration(skipper.isLateRegistration());
        update.setLunch(skipper.isLunch());
        update.setEntryFee(skipper.isEntryFee());
        update.setCatering(skipper.isCatering());
        return skipperService.save(update);
    }

}
