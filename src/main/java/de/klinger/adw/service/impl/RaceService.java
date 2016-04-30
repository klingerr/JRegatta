package de.klinger.adw.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.Race;
import de.klinger.adw.repository.RaceRepository;

@Service
public class RaceService {
	
	@Autowired
	private RaceRepository raceRepository;
	
	public Race saveRace(Race race) {
		return raceRepository.save(race);
	}
	
	public List<Race> getAllRaces() {
		return raceRepository.findAll();
	}

	public void delete(String id) {
		raceRepository.delete(new BigInteger(id));
	}

	public Race findOne(String id) {
		return raceRepository.findOne(new BigInteger(id));
	}

	public Race save(Race race) {
		return raceRepository.save(race);
	}

}
