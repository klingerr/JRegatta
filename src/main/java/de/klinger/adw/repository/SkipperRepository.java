package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Skipper;

public interface SkipperRepository extends CrudRepository<Skipper, Long> {

    @Override
    List<Skipper> findAll();
    
    List<Skipper> findAllByRegattaId(Long id);

    Skipper findOneBySailNumberAndRegattaId(String sailNumber, long regattaId);

}
