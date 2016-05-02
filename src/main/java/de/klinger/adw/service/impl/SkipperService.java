package de.klinger.adw.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.Skipper;
import de.klinger.adw.repository.SkipperRepository;

@Service
public class SkipperService {
	
	@Autowired
	private SkipperRepository skipperRepository;
	
	public Skipper saveSkipper(Skipper skipper) {
		return skipperRepository.save(skipper);
	}
	
	public List<Skipper> getAllSkippersByRegattaId(Long id) {
		return skipperRepository.findAllByRegattaId(id);
	}

	public void delete(String id) {
		skipperRepository.delete(new BigInteger(id));
	}

	public Skipper findOne(String id) {
		return skipperRepository.findOne(new BigInteger(id));
	}

	public Skipper save(Skipper skipper) {
		return skipperRepository.save(skipper);
	}

}
