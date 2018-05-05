package de.klinger.adw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.AgeGroup;
import de.klinger.adw.domain.Result;
import de.klinger.adw.repository.ResultRepository;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public void saveResults(List<Result> resultList) {
    	resultRepository.save(resultList);
    }
    
    public List<Result> getAllByRaceIdOrderByAgeGroupAndPlacement(Long raceId) {
    	return resultRepository.findAllByRaceIdOrderBySkipperAgeGroupDescPlacementAsc(raceId);
    }
    
    public List<Result> getAllByRaceIdAndSkipperAgeGroupOrderByPlacement(Long raceId, AgeGroup ageGroup) {
    	return resultRepository.findAllByRaceIdAndSkipperAgeGroupOrderByPlacementAsc(raceId, ageGroup);
    }
    
    public List<Result> getAllByRaceIdOrderByPlacement(Long raceId) {
        return resultRepository.findAllByRaceIdOrderByPlacementAsc(raceId);
    }

    public List<Result> findAllByRaceIdOrderByPlacementAsc(Long raceId) {
        return resultRepository.findAllByRaceIdOrderByPlacementAsc(raceId);
    }

    public List<Result> getAllByRaceRegattaIdOrderBySkipper(Long regattaId) {
        return resultRepository.findAllByRaceRegattaIdOrderBySkipper(regattaId);
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
