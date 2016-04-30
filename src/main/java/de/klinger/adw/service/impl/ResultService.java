package de.klinger.adw.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.Result;
import de.klinger.adw.repository.ResultRepository;

@Service
public class ResultService {
	
	@Autowired
	private ResultRepository resultRepository;
	
	public Result saveResult(Result result) {
		return resultRepository.save(result);
	}
	
	public List<Result> getAllResults() {
		return resultRepository.findAll();
	}

	public void delete(String id) {
		resultRepository.delete(new BigInteger(id));
	}

	public Result findOne(String id) {
		return resultRepository.findOne(new BigInteger(id));
	}

	public Result save(Result result) {
		return resultRepository.save(result);
	}

}
