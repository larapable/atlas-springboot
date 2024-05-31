package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.InternalReportEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.InternalReportRepository;

@Service
public class InternalReportService {
    @Autowired
    InternalReportRepository internalReportRepo;
    @Autowired
    DepartmentRepository departmentRepo;

    // Get all internal reports
    public List<InternalReportEntity> getAllInternalReports() {
        List<InternalReportEntity> reports = internalReportRepo.findAll();
        if (reports.isEmpty()) {
            throw new NoSuchElementException("No internal reports found.");
        }
        return reports;
    }

    // Get all internal reports by department id
    public List<InternalReportEntity> getInternalReportsByDepartmentId(int departmentId) {
        return internalReportRepo.findByDepartmentId(departmentId);
    }

    // Insert a new internal report
    public InternalReportEntity insertInternalReport(int departmentId, InternalReportEntity request) {
        try {
            // Fetch the department entity by its ID
            DepartmentEntity department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new NoSuchElementException("Department not found for id: " + departmentId));

            // Set the department entity to the internal report entity
            request.setDepartment(department);

            // Save and return the internal report entity
            return internalReportRepo.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to insert internal report.", e);
        }
    }

    // Update an existing internal report
    public InternalReportEntity updateInternalReport(int reportId, InternalReportEntity updatedReport) {
        try {
            // Fetch the existing internal report entity by its ID
            InternalReportEntity existingReport = internalReportRepo.findById(reportId)
                    .orElseThrow(() -> new NoSuchElementException("Internal report not found for id: " + reportId));

            // Update the existing internal report entity
            existingReport.setTitle(updatedReport.getTitle());
            existingReport.setDescription(updatedReport.getDescription());
            existingReport.setObjectives(updatedReport.getObjectives());

            // Save and return the updated internal report entity
            return internalReportRepo.save(existingReport);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to update internal report.", e);
        }
    }

    // Delete an internal report
    public String deleteInternalReport(int id) {
        Optional<InternalReportEntity> optionalReport = internalReportRepo.findById(id);
        if (optionalReport.isPresent()) {
            InternalReportEntity report = optionalReport.get();
            // Remove objectives if needed
            report.removeObjectives(report.getObjectives());
            internalReportRepo.deleteById(id);
            return "Internal Report Id" + id + " has been deleted!";
        } else {
            return "Internal Report Id" + id + " not found!";
        }
    }
}
