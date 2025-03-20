package org.example.service;

import org.example.model.Persoa;
import org.example.repository.PersoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersoaService {

    @Autowired
    private PersoaRepository persoaRepository;

    @Autowired
    public PersoaService(PersoaRepository persoaRepository) {
        this.persoaRepository = persoaRepository;
    }


    public Persoa upsert(Persoa persoa) {
        return persoaRepository.save(persoa);
    }

    public List<Persoa> list() {
        return persoaRepository.findAll();
    }

    public Optional<Persoa> getById(String id) {
        return persoaRepository.findById(id);
    }
}
