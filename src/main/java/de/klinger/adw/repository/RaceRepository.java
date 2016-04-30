package de.klinger.adw.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Race;

public interface RaceRepository extends CrudRepository<Race, BigInteger> {

    @Override
    List<Race> findAll();
    
//    List<Race> findAllByRegattaByNumberAsc();

}
