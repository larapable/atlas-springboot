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

import com.example.Atlas.Entity.InternalReportEntity;
import com.example.Atlas.Service.InternalReportService;

@RestController
@RequestMapping("/internalReport")
@CrossOrigin
public class InternalReportController {
    @Autowired
    InternalReportService internalReportService;

    // Insert an internal report
    @PostMapping("/insertInternalReport/{departmentId}")
    public ResponseEntity<?> insertInternalReport(@PathVariable int departmentId,
            @RequestBody InternalReportEntity request) {
        try {
            request.setDateCreated(LocalDate.now());

            // Insert internal report
            InternalReportEntity insertedReport = internalReportService.insertInternalReport(departmentId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedReport);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert internal report.");
        }
    }

    // Update an internal report
    @PutMapping("/updateInternalReport/{id}")
    public ResponseEntity<String> updateInternalReport(@PathVariable int id,
            @RequestBody InternalReportEntity request) {
        try {
            internalReportService.updateInternalReport(id, request);
            return new ResponseEntity<>("Internal report updated.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while updating the internal report.");
        }
    }

    // Delete an internal report
    @DeleteMapping("/deleteInternalReport/{id}")
    public ResponseEntity<String> deleteInternalReport(@PathVariable int id) {
        try {
            internalReportService.deleteInternalReport(id);
            return new ResponseEntity<>("Internal report deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while deleting the internal report.");
        }
    }

    // Get all internal reports
    @GetMapping("/getAllInternalReport")
    public ResponseEntity<List<InternalReportEntity>> getAllInternalReports() {
        try {
            List<InternalReportEntity> reports = internalReportService.getAllInternalReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching internal reports.");
        }
    }

}
