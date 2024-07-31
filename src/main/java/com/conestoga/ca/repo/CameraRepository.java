package com.conestoga.ca.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.conestoga.ca.model.Camera;

	public interface CameraRepository extends MongoRepository<Camera, String> {
	}