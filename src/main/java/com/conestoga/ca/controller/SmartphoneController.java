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

import com.conestoga.ca.model.Smartphone;
import com.conestoga.ca.repo.SmartphoneRepository;
import com.conestoga.ca.utill.ResourceNotFoundException;

@RestController
@RequestMapping("/api/smartphones")
public class SmartphoneController {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @GetMapping
    public List<Smartphone> getAllSmartphones() {
        return smartphoneRepository.findAll();
    }

    @PostMapping
    public Smartphone addSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Smartphone> updateSmartphone(@PathVariable String id, @RequestBody Smartphone smartphoneDetails) {
        Smartphone smartphone = smartphoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Smartphone not found with id: " + id));
        
        smartphone.setBrand(smartphoneDetails.getBrand());
        smartphone.setModel(smartphoneDetails.getModel());

        Smartphone updatedSmartphone = smartphoneRepository.save(smartphone);
        return ResponseEntity.ok(updatedSmartphone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSmartphone(@PathVariable String id) {
        Smartphone smartphone = smartphoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Smartphone not found with id: " + id));

        smartphoneRepository.delete(smartphone);
        return ResponseEntity.ok().build();
    }
}