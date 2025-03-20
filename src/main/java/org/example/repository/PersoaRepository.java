package org.example.repository;

import org.example.model.Persoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersoaRepository extends MongoRepository<Persoa, String> {

}
