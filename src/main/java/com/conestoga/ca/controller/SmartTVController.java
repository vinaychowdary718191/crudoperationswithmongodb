package com.conestoga.ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conestoga.ca.model.SmartTV;
import com.conestoga.ca.repo.SmartTVRepository;
import com.conestoga.ca.utill.ResourceNotFoundException;

@RestController
@RequestMapping("/api/smarttvs")
public class SmartTVController {
	@Autowired
	private SmartTVRepository smartTVRepository;

	@GetMapping
	public List<SmartTV> getAllSmartTVs() {
		return smartTVRepository.findAll();
	}

	@PostMapping
	public SmartTV addSmartTV(@RequestBody SmartTV smartTV) {
		return smartTVRepository.save(smartTV);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SmartTV> updateSmartTV(@PathVariable String id, @RequestBody SmartTV smartTVDetails) {
		SmartTV smartTV = smartTVRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SmartTV not found with id: " + id));

		smartTV.setBrand(smartTVDetails.getBrand());
		smartTV.setModel(smartTVDetails.getModel());

		SmartTV updatedSmartTV = smartTVRepository.save(smartTV);
		return ResponseEntity.ok(updatedSmartTV);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSmartTV(@PathVariable String id) {
		SmartTV smartTV = smartTVRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SmartTV not found with id: " + id));

		smartTVRepository.delete(smartTV);
		return ResponseEntity.ok().build();
	}
}