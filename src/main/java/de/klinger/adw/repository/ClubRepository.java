package de.klinger.adw.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Club;

public interface ClubRepository extends CrudRepository<Club, BigInteger> {

    @Override
    List<Club> findAll();
    
}
