package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Race;

public interface RaceRepository extends CrudRepository<Race, Long> {

//    @Override
//    List<Race> findAll();
    
    List<Race> findAllByRegattaIdOrderByNumberAsc(Long regattaId);

}
