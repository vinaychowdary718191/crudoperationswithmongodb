package com.conestoga.ca.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.conestoga.ca.model.Laptop;

public interface LaptopRepository extends MongoRepository<Laptop, String> {
}
