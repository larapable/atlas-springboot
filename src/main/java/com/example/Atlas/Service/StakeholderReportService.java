package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.StakeholderReportEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.StakeholderReportRepository;

@Service
public class StakeholderReportService {
    @Autowired
    StakeholderReportRepository stakeholderReportRepo;
    @Autowired
    DepartmentRepository departmentRepo;

    // Get all stakeholder reports
    public List<StakeholderReportEntity> getAllStakeholderReports() {
        List<StakeholderReportEntity> reports = stakeholderReportRepo.findAll();
        if (reports.isEmpty()) {
            throw new NoSuchElementException("No stakeholder reports found.");
        }
        return reports;
    }

    // Get all stakeholder reports by department id
    public List<StakeholderReportEntity> getStakeholderReportsByDepartmentId(int departmentId) {
        return stakeholderReportRepo.findByDepartmentId(departmentId);
    }

    // Insert a new stakeholder report
    public StakeholderReportEntity insertStakeholderReport(int departmentId, StakeholderReportEntity request) {
        try {
            // Fetch the department entity by its ID
            DepartmentEntity department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new NoSuchElementException("Department not found for id: " + departmentId));

            // Set the department entity to the stakeholder report entity
            request.setDepartment(department);

            // Save and return the stakeholder report entity
            return stakeholderReportRepo.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to insert stakeholder report.", e);
        }
    }

    // Update an existing stakeholder report
    public StakeholderReportEntity updateStakeholderReport(int reportId, StakeholderReportEntity updatedReport) {
        try {
            // Fetch the existing stakeholder report entity by its ID
            StakeholderReportEntity existingReport = stakeholderReportRepo.findById(reportId)
                    .orElseThrow(() -> new NoSuchElementException("Stakeholder report not found for id: " + reportId));

            // Update the existing stakeholder report entity
            existingReport.setTitle(updatedReport.getTitle());
            existingReport.setDescription(updatedReport.getDescription());
            existingReport.setObjectives(updatedReport.getObjectives());

            // Save and return the updated stakeholder report entity
            return stakeholderReportRepo.save(existingReport);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to update stakeholder report.", e);
        }
    }

    // Delete a stakeholder report by id
    public String deleteStakeholderReport(int id) {
        Optional<StakeholderReportEntity> optionalReport = stakeholderReportRepo.findById(id);
        if (optionalReport.isPresent()) {
            StakeholderReportEntity report = optionalReport.get();
            // Remove objects if needed
            report.removeObjectives(report.getObjectives());
            stakeholderReportRepo.deleteById(id);
            return "Stakeholder Report Id " + id + " has been deleted!";
        } else {
            return "Stakeholder Report Id " + id + " not found!";
        }
    }
}
