package de.klinger.adw.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Result;

public interface ResultRepository extends CrudRepository<Result, BigInteger> {

    @Override
    List<Result> findAll();
    
//    List<Result> findAllByRace();

}
