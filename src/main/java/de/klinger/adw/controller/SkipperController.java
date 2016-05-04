package de.klinger.adw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Skipper;
import de.klinger.adw.service.impl.SkipperService;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/regattas/{regattaId}/skippers")
public class SkipperController {

    @Autowired
    private SkipperService skipperService;
//    @Autowired
//    private ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<Skipper> getAllByRegattaId(@PathVariable Long regattaId) {
        return skipperService.getAllSkippersByRegattaId(regattaId);
    }
//    public List<SkipperDto> getAll() {
//        List<SkipperDto> skipperDtos = new ArrayList<>();
//        for (Skipper skipper : skipperService.getAllSkippers()) {
//            skipperDtos.add(mapper.map(skipper, SkipperDto.class));
//        }
//        return skipperDtos;
//    }

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
        update.setAgeGroup(skipper.getAgeGroup());
        update.setSailNumber(skipper.getSailNumber());
        update.setClub(skipper.getClub());
        update.setLateRegistration(skipper.isLateRegistration());
        update.setLunch(skipper.isLunch());
        update.setEntryFee(skipper.isEntryFee());
        update.setCatering(skipper.isCatering());
        return skipperService.save(update);
    }

}
