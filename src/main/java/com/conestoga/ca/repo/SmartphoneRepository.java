package com.conestoga.ca.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.conestoga.ca.model.Smartphone;

public interface SmartphoneRepository extends MongoRepository<Smartphone, String> {
}