package de.klinger.adw.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Skipper;

public interface SkipperRepository extends CrudRepository<Skipper, BigInteger> {

    @Override
    List<Skipper> findAll();
    
//    List<Skipper> findAllByRegatta();

}
