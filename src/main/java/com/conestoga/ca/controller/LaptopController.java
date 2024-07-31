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

import com.conestoga.ca.model.Laptop;
import com.conestoga.ca.repo.LaptopRepository;
import com.conestoga.ca.utill.ResourceNotFoundException;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {
    @Autowired
    private LaptopRepository laptopRepository;
    @GetMapping
    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @PostMapping
    public Laptop addLaptop(@RequestBody Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable String id, @RequestBody Laptop laptopDetails) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laptop not found with id: " + id));
        
        laptop.setBrand(laptopDetails.getBrand());
        laptop.setModel(laptopDetails.getModel());

        Laptop updatedLaptop = laptopRepository.save(laptop);
        return ResponseEntity.ok(updatedLaptop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaptop(@PathVariable String id) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laptop not found with id: " + id));

        laptopRepository.delete(laptop);
        return ResponseEntity.ok().build();
    }
}