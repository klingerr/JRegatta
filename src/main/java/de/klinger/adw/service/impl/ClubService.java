package de.klinger.adw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.Club;
import de.klinger.adw.repository.ClubRepository;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public void delete(String id) {
        clubRepository.delete(new Long(id));
    }

    public Club findOne(String id) {
        return clubRepository.findOne(new Long(id));
    }

    public Club findOneByShortName(String shortName) {
        return clubRepository.findOneByShortName(shortName);
    }

    public Club save(Club club) {
        return clubRepository.save(club);
    }

}
