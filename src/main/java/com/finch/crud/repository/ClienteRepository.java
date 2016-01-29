package com.finch.crud.repository;

import com.finch.crud.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository do cliente responsável pelas operações no Mongo.
 * 
 * @author guilherme.carvalho
 */
public interface ClienteRepository extends MongoRepository<Cliente, Long> {
}
