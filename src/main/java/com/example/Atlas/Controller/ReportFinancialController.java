package com.example.Atlas.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Atlas.Entity.ReportFinancialEntity;
import com.example.Atlas.Service.ReportFinancialService;

@RestController
@RequestMapping("/reportFinancial")
@CrossOrigin
public class ReportFinancialController {

    @Autowired
    ReportFinancialService reportFinancialService;

    // Insert a financial report
    @PostMapping("/insertFinancialReport/{departmentId}")
    public ResponseEntity<?> insertFinancialReport(@PathVariable int departmentId,
            @RequestBody ReportFinancialEntity request) {
        try {
            // Insert financial report
            ReportFinancialEntity insertedReport = reportFinancialService.insertFinancialReport(departmentId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedReport);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert financial report.");
        }
    }

    // Get all financial reports
    @GetMapping("/getAllFinancialReport")
    public ResponseEntity<List<ReportFinancialEntity>> getAllFinancialReports() {
        try {
            List<ReportFinancialEntity> reports = reportFinancialService.getAllFinancialReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while fetching financial reports.");
        }
    }

    // Update a financial report
    @PostMapping("/updateFinancialReport/{reportId}")
    public ResponseEntity<?> updateFinancialReport(@PathVariable int reportId,
            @RequestBody ReportFinancialEntity updatedReport) {
        try {
            ReportFinancialEntity report = reportFinancialService.updateFinancialReport(reportId, updatedReport);
            return ResponseEntity.ok(report);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update financial report.");
        }
    }

}
