package de.klinger.adw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.klinger.adw.domain.Club;

public interface ClubRepository extends CrudRepository<Club, Long> {

    @Override
    List<Club> findAll();

    public Club findOneByShortName(String shortName);
    
}
