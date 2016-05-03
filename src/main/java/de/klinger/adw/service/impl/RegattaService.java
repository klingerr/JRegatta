package de.klinger.adw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klinger.adw.domain.Regatta;
import de.klinger.adw.repository.RegattaRepository;

@Service
public class RegattaService {

    @Autowired
    private RegattaRepository regattaRepository;

    public Regatta getRegattaByName(String name) {
        return regattaRepository.findByName(name);
    }

    public Regatta saveRegatta(Regatta regatta) {
        return regattaRepository.save(regatta);
    }

    public List<Regatta> getAllRegattas() {
        return regattaRepository.findAllByOrderByStartDateDesc();
    }

    public void delete(String id) {
        regattaRepository.delete(new Long(id));
    }

    public Regatta findOne(String id) {
        return regattaRepository.findOne(new Long(id));
    }

    public Regatta save(Regatta regatta) {
        return regattaRepository.save(regatta);
    }

}
