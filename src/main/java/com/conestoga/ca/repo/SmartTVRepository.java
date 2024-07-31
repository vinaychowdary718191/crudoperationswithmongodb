package com.conestoga.ca.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.conestoga.ca.model.SmartTV;

public interface SmartTVRepository extends MongoRepository<SmartTV, String> {
}