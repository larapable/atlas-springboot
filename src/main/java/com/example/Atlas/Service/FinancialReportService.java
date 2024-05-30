package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FinancialReportEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialReportRepository;

@Service
public class FinancialReportService {
    @Autowired
    FinancialReportRepository financialReportRepo;
    @Autowired
    DepartmentRepository departmentRepo;

    // Get all financial reports
    public List<FinancialReportEntity> getAllFinancialReports() {
        List<FinancialReportEntity> reports = financialReportRepo.findAll();
        if (reports.isEmpty()) {
            throw new NoSuchElementException("No financial reports found.");
        }
        return reports;
    }

    // Get all financial reports by department id
    public List<FinancialReportEntity> getFinancialReportsByDepartmentId(int departmentId) {
        return financialReportRepo.findByDepartmentId(departmentId);
    }

    // Insert a new financial report
    public FinancialReportEntity insertFinancialReport(int departmentId, FinancialReportEntity request) {
        try {
            // Fetch the department entity by its ID
            DepartmentEntity department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new NoSuchElementException("Department not found for id: " + departmentId));

            // Set the department entity to the financial report entity
            request.setDepartment(department);

            // Save and return the financial report entity
            return financialReportRepo.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to insert financial report.", e);
        }
    }

    // Update an existing financial report
    public FinancialReportEntity updateFinancialReport(int reportId, FinancialReportEntity updatedReport) {
        try {
            // Fetch the existing financial report entity by its ID
            FinancialReportEntity existingReport = financialReportRepo.findById(reportId)
                    .orElseThrow(() -> new NoSuchElementException("Financial report not found for id: " + reportId));

            // Update the fields of the existing report with the values from the updated
            // report
            existingReport.setTitle(updatedReport.getTitle());
            existingReport.setDescription(updatedReport.getDescription());
            existingReport.setObjectives(updatedReport.getObjectives());

            // Save and return the updated financial report entity
            return financialReportRepo.save(existingReport);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to update financial report.", e);
        }
    }

    // Delete a financial report by id
    public String deleteFinancialReport(int id) {
        Optional<FinancialReportEntity> optionalReport = financialReportRepo.findById(id);
        if (optionalReport.isPresent()) {
            FinancialReportEntity report = optionalReport.get();
            // Remove objectives if needed
            report.removeObjectives(report.getObjectives());
            financialReportRepo.deleteById(id);
            return "Financial Report Id " + id + " has been deleted!";
        } else {
            return "Financial Report Id " + id + " does not exist!";
        }
    }

}
