package de.klinger.adw.service.impl;

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

    public List<Result> getAllByRaceId(Long raceId) {
        return resultRepository.findAllByRaceIdOrderByPlacement(raceId);
    }

    public void delete(String id) {
        resultRepository.delete(new Long(id));
    }

    public Result findOne(String id) {
        return resultRepository.findOne(new Long(id));
    }

    public Result save(Result result) {
        return resultRepository.save(result);
    }

}
