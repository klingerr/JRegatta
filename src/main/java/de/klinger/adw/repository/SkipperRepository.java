package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.AgeGroup;
import de.klinger.adw.domain.Skipper;

public interface SkipperRepository extends CrudRepository<Skipper, Long> {

    @Override
    List<Skipper> findAll();
    
    List<Skipper> findAllByRegattaIdOrderBySailNumberAsc(Long id);
    List<Skipper> findAllByRegattaIdOrderByClubAscLastNameAscFirstNameAsc(Long id);

    Integer countByRegattaIdAndAgeGroup(Long regattaId, AgeGroup ageGroup);

    @Query(" SELECT s " + 
    		"FROM Skipper s " + 
    		"WHERE s.regatta.id = ?1 " + 
    		"  AND s.id not in (SELECT r.skipper.id " + 
    		"                   FROM Result r  " + 
    		"                   where r.skipper.id is not null"
    		+ "                   and r.race.id = ?2) "
    		+ " order by s.sailNumber asc")
    List<Skipper> findAvailableByRaceIdOrderBySailNumberAsc(long regattaId, long raceId);

    Skipper findOneBySailNumberAndRegattaId(String sailNumber, long regattaId);

}
