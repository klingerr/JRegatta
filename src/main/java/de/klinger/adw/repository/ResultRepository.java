package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {

//    @Override
//    List<Result> findAll();
    
//    List<Result> findAllByRaceIdOrderByPlacement(Long raceId);

    List<Result> findAllByRaceRegattaIdOrderBySkipper(Long regattaId);

    List<Result> findAllByRaceIdOrderBySkipperAgeGroupAscPlacementAsc(Long raceId);

}
