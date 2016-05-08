package de.klinger.adw.controller;

import de.klinger.adw.domain.AgeGroup;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Skipper;
import de.klinger.adw.service.impl.ClubService;
import de.klinger.adw.service.impl.SkipperService;

@RestController
@RequestMapping("/regattas/{regattaId}/skippers")
public class SkipperController {

    @Autowired
    private SkipperService skipperService;
    @Autowired
    private ClubService clubService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Skipper> getAllByRegattaId(@PathVariable Long regattaId) {
        return skipperService.getAllSkippersByRegattaIdOrderBySailNumberAsc(regattaId);
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

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Skipper update(@PathVariable String id, @RequestBody Skipper skipper) {
        Skipper update = skipperService.findOne(id);
        update.setLastName(skipper.getLastName());
        update.setFirstName(skipper.getFirstName());
        update.setGender(skipper.getGender());
        update.setBirthDay(skipper.getBirthDay());
        update.setAgeGroup(AgeGroup.getAgeGroupByBirthday(skipper.getBirthDay()));
        update.setSailNumber(skipper.getSailNumber());
        update.setClub(clubService.findOneByShortName(skipper.getClub().getShortName()));
        update.setLateRegistration(skipper.isLateRegistration());
        update.setLunch(skipper.isLunch());
        update.setEntryFee(skipper.isEntryFee());
        update.setCatering(skipper.isCatering());
        return skipperService.save(update);
    }

}
