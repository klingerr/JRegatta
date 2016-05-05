package de.klinger.adw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.klinger.adw.domain.Race;
import de.klinger.adw.service.impl.RaceService;

@RestController
@RequestMapping("/regattas/{regattaId}/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

//    @Autowired
//    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<Race> getAllByRegattaId(@PathVariable Long regattaId) {
        return raceService.getAllRacesByRegattaIdOrderByNumberAsc(regattaId);
    }
//    public List<RaceDto> getAll() {
//        List<RaceDto> raceDtos = new ArrayList<>();
//        for (Race race : raceService.getAllRaces()) {
//            raceDtos.add(mapper.map(race, RaceDto.class));
//        }
//        return raceDtos;
//    }

    @RequestMapping(method = RequestMethod.POST)
    public Race create(@RequestBody Race race) {
        return raceService.saveRace(race);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Race getOne(@PathVariable String id) {
        return raceService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable String id) {
        raceService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Race update(@PathVariable String id, @RequestBody Race race) {
        Race update = raceService.findOne(id);
        update.setStartTime(race.getStartTime());
        update.setEndTime(race.getEndTime());
        update.setRegatta(race.getRegatta());
        return raceService.save(update);
    }

}
