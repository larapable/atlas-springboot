package com.example.Atlas.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.Atlas.Entity.FinancialReportEntity;
import com.example.Atlas.Service.FinancialReportService;

@RestController
@RequestMapping("/financialReport")
@CrossOrigin
public class FinancialReportController {

    @Autowired
    FinancialReportService financialReportService;

    // Insert a financial report
    @PostMapping("/insertFinancialReport/{departmentId}")
    public ResponseEntity<?> insertFinancialReport(@PathVariable int departmentId,
            @RequestBody FinancialReportEntity request) {
        try {
            // Set the dateCreated property
            request.setDateCreated(LocalDate.now());

            // Insert financial report
            FinancialReportEntity insertedReport = financialReportService.insertFinancialReport(departmentId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedReport);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert financial report.");
        }
    }

    // Update a financial report
    @PutMapping("/updateFinancialReport/{id}")
    public ResponseEntity<String> updateFinancialReport(@PathVariable int id,
            @RequestBody FinancialReportEntity request) {
        try {
            financialReportService.updateFinancialReport(id, request);
            return new ResponseEntity<>("Financial report updated.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while updating the financial report.");
        }
    }

    // Delete a financial report
    @DeleteMapping("/deleteFinancialReport/{id}")
    public ResponseEntity<String> deleteFinancialReport(@PathVariable int id) {
        try {
            financialReportService.deleteFinancialReport(id);
            return new ResponseEntity<>("Financial report deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while deleting the financial report.");
        }
    }

    // Get all financial reports
    @GetMapping("/getAllFinancialReport")
    public ResponseEntity<List<FinancialReportEntity>> getAllFinancialReports() {
        try {
            List<FinancialReportEntity> reports = financialReportService.getAllFinancialReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching financial reports.");
        }
    }

    // Get financial reports by department ID
    @GetMapping("/getFinancialReportByDepartmentId/{departmentId}")
    public ResponseEntity<List<FinancialReportEntity>> getFinancialReportsByDepartmentId(
            @RequestParam int departmentId) {
        try {
            List<FinancialReportEntity> reports = financialReportService
                    .getFinancialReportsByDepartmentId(departmentId);
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching financial reports by department ID.");
        }
    }
}
