package com.example.BarangayConnect.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.ProfileEntity;
import com.example.BarangayConnect.Service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService pservice;

    // Create
    @PostMapping("insertProfile")
    public ProfileEntity insertProfile(@RequestBody ProfileEntity profileEntity) {
        return pservice.insertProfile(profileEntity);
    }

    // Read
    @GetMapping("getAllProfile")
    public ResponseEntity<List<ProfileEntity>> getAllProfile() {
        List<ProfileEntity> allProfiles = pservice.getAllProfile();
        return new ResponseEntity<>(allProfiles, HttpStatus.OK);
    }

    // Update
    @PutMapping("updateProfile")
    public ProfileEntity updateProfile(@RequestParam int id, @RequestBody ProfileEntity newProfileDetails) {
        return pservice.updateProfile(id, newProfileDetails);
    }

    // Delete
    @DeleteMapping("deleteProfile/{id}")
    public String deleteProfile(@PathVariable int id) {
        return pservice.deleteProfile(id);
    }
}
