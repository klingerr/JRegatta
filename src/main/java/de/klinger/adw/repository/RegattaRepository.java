package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Regatta;

public interface RegattaRepository extends CrudRepository<Regatta, Long> {

    @Override
    List<Regatta> findAll();
    
    List<Regatta> findAllByOrderByStartDateDesc();

    Regatta findByName(String name);

}
