package com.example.Atlas.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Atlas.Entity.LearningReportEntity;
import com.example.Atlas.Service.LearningReportService;

@RestController
@RequestMapping("/learningReport")
@CrossOrigin
public class LearningReportController {
    @Autowired
    LearningReportService learningReportService;

    // Insert a learning report
    @PostMapping("/insertLearningReport/{departmentId}")
    public ResponseEntity<?> insertLearningReport(@PathVariable int departmentId,
            @RequestBody LearningReportEntity request) {
        try {
            // Set the dateCreated property
            request.setDateCreated(LocalDate.now());

            // Insert learning report
            LearningReportEntity insertedReport = learningReportService.insertLearningReport(departmentId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedReport);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert learning report.");
        }
    }

    // Update a learning report
    @PutMapping("/updateLearningReport/{id}")
    public ResponseEntity<String> updateLearningReport(@PathVariable int id,
            @RequestBody LearningReportEntity request) {
        try {
            learningReportService.updateLearningReport(id, request);
            return new ResponseEntity<>("Learning report updated.", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the learning report.");
        }
    }

    // Delete a learning report
    @DeleteMapping("/deleteLearningReport/{id}")
    public ResponseEntity<String> deleteLearningReport(@PathVariable int id) {
        try {
            learningReportService.deleteLearningReport(id);
            return new ResponseEntity<>("Learning report deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the learning report.");
        }
    }

    // Get all learning reports
    @GetMapping("/getAllLearningReport")
    public ResponseEntity<List<LearningReportEntity>> getAllLearningReports() {
        try {
            List<LearningReportEntity> reports = learningReportService.getAllLearningReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching learning reports.");
        }
    }
}
