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

import com.conestoga.ca.model.Camera;
import com.conestoga.ca.repo.CameraRepository;
import com.conestoga.ca.utill.ResourceNotFoundException;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {
    @Autowired
    private CameraRepository cameraRepository;
    @GetMapping
    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    @PostMapping
    public Camera addCamera(@RequestBody Camera Camera) {
        return cameraRepository.save(Camera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Camera> updateCamera(@PathVariable String id, @RequestBody Camera CameraDetails) {
        Camera Camera = cameraRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Camera not found with id: " + id));
        
        Camera.setBrand(CameraDetails.getBrand());
        Camera.setModel(CameraDetails.getModel());

        Camera updatedCamera = cameraRepository.save(Camera);
        return ResponseEntity.ok(updatedCamera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCamera(@PathVariable String id) {
        Camera Camera = cameraRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Camera not found with id: " + id));

        cameraRepository.delete(Camera);
        return ResponseEntity.ok().build();
    }
}