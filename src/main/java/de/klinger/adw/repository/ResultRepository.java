package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.AgeGroup;
import de.klinger.adw.domain.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {

	List<Result> findAllByRaceRegattaIdOrderBySkipper(Long regattaId);

	List<Result> findAllByRaceIdOrderBySkipperAgeGroupAscPointsAsc(Long raceId);

	List<Result> findAllByRaceIdOrderBySkipperAgeGroupDescPlacementAsc(Long raceId);

	List<Result> findAllByRaceIdAndSkipperAgeGroupOrderByPlacementAsc(Long raceId, AgeGroup ageGroup);

	List<Result> findAllByRaceId(Long raceId);

	List<Result> findAllByRaceIdOrderByPlacementAsc(Long raceId);

}
