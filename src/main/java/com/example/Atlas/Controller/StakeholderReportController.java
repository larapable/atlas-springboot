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

import com.example.Atlas.Entity.StakeholderReportEntity;
import com.example.Atlas.Service.StakeholderReportService;

@RestController
@RequestMapping("/stakeholderReport")
@CrossOrigin
public class StakeholderReportController {
    @Autowired
    StakeholderReportService stakeholderReportService;

    // Insert a stakeholder report
    @PostMapping("/insertStakeholderReport/{departmentId}")
    public ResponseEntity<?> insertStakeholderReport(@PathVariable int departmentId,
            @RequestBody StakeholderReportEntity request) {
        try {
            // Set the dateCreated property
            request.setDateCreated(LocalDate.now());

            // Insert stakeholder report
            StakeholderReportEntity insertedReport = stakeholderReportService.insertStakeholderReport(departmentId,
                    request);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedReport);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert stakeholder report.");
        }
    }

    // Update a stakeholder report
    @PutMapping("/updateStakeholderReport/{id}")
    public ResponseEntity<String> updateStakeholderReport(@PathVariable int id,
            @RequestBody StakeholderReportEntity request) {
        try {
            stakeholderReportService.updateStakeholderReport(id, request);
            return new ResponseEntity<>("Stakeholder report updated.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while updating the stakeholder report.");
        }
    }

    // Delete a stakeholder report
    @DeleteMapping("/deleteStakeholderReport/{id}")
    public ResponseEntity<String> deleteStakeholderReport(@PathVariable int id) {
        try {
            stakeholderReportService.deleteStakeholderReport(id);
            return new ResponseEntity<>("Stakeholder report deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while deleting the stakeholder report.");
        }
    }

    // Get all stakeholder reports
    @GetMapping("/getAllStakeholderReport")
    public ResponseEntity<List<StakeholderReportEntity>> getAllStakeholderReports() {
        try {
            List<StakeholderReportEntity> reports = stakeholderReportService.getAllStakeholderReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching stakeholder reports.");
        }
    }
}
