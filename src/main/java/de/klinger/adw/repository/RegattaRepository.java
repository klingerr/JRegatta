package de.klinger.adw.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Regatta;

public interface RegattaRepository extends CrudRepository<Regatta, BigInteger> {

    @Override
    List<Regatta> findAll();
    
    List<Regatta> findAllByOrderByNameAsc();

	Regatta findByName(String name);

}
