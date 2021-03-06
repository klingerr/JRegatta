package de.klinger.adw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.AgeGroup;
import de.klinger.adw.domain.Skipper;
import de.klinger.adw.repository.SkipperRepository;

@Service
public class SkipperService {

    @Autowired
    private SkipperRepository skipperRepository;

    public Skipper saveSkipper(Skipper skipper) {
        return skipperRepository.save(skipper);
    }

    public List<Skipper> getAllSkippersByRegattaIdOrderBySailNumberAsc(Long regattaId) {
        return skipperRepository.findAllByRegattaIdOrderByClubAscLastNameAscFirstNameAsc(regattaId);
    }

    public List<Skipper> getAvailableByRaceIdOrderBySailNumberAsc(Long regattaId, Long raceId) {
    	return skipperRepository.findAvailableByRaceIdOrderBySailNumberAsc(regattaId, raceId);
    }
    
    public Integer countByRegattaIdAndAgeGroup(Long regattaId, AgeGroup ageGroup) {
    	return skipperRepository.countByRegattaIdAndAgeGroup(regattaId, ageGroup);
    }
    
    public Integer countByRegattaId(Long regattaId) {
    	return skipperRepository.countByRegattaId(regattaId);
    }
    
    public void delete(String id) {
        skipperRepository.delete(new Long(id));
    }

    public Skipper findOne(String id) {
        return skipperRepository.findOne(new Long(id));
    }

    public Skipper findOneBySailNumberAndRegattaId(String sailNumber, long regattaId) {
        return skipperRepository.findOneBySailNumberAndRegattaId(sailNumber, regattaId);
    }

    public Skipper save(Skipper skipper) {
        return skipperRepository.save(skipper);
    }

}
