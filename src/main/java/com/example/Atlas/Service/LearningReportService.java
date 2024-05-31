package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.LearningReportEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.LearningReportRepository;

@Service
public class LearningReportService {
    @Autowired
    LearningReportRepository learningReportRepo;
    @Autowired
    DepartmentRepository departmentRepo;

    // Get all learning reports
    public List<LearningReportEntity> getAllLearningReports() {
        List<LearningReportEntity> reports = learningReportRepo.findAll();
        if (reports.isEmpty()) {
            throw new NoSuchElementException("No learning reports found.");
        }
        return reports;
    }

    // Get all learning reports by department id
    public List<LearningReportEntity> getLearningReportsByDepartmentId(int departmentId) {
        return learningReportRepo.findByDepartmentId(departmentId);
    }

    // Insert a new learning report
    public LearningReportEntity insertLearningReport(int departmentId, LearningReportEntity request) {
        try {
            // Fetch the department entity by its ID
            DepartmentEntity department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new NoSuchElementException("Department not found for id: " + departmentId));

            // Set the department entity to the learning report entity
            request.setDepartment(department);

            // Save and return the learning report entity
            return learningReportRepo.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to insert learning report.", e);
        }
    }

    // Update an existing learning report
    public LearningReportEntity updateLearningReport(int reportId, LearningReportEntity updatedReport) {
        try {
            // Fetch the existing learning report entity by its ID
            LearningReportEntity existingReport = learningReportRepo.findById(reportId)
                    .orElseThrow(() -> new NoSuchElementException("Learning report not found for id: " + reportId));

            // Update the existing learning report entity
            existingReport.setTitle(updatedReport.getTitle());
            existingReport.setDescription(updatedReport.getDescription());
            existingReport.setObjectives(updatedReport.getObjectives());

            // Save and return the updated learning report entity
            return learningReportRepo.save(existingReport);
        } catch (Exception e) {
            e.printStackTrace();
            // You may choose to handle the exception differently or rethrow it
            throw new RuntimeException("Failed to update learning report.", e);
        }
    }

    // Delete a learning report by id
    public String deleteLearningReport(int id) {
        Optional<LearningReportEntity> optionalReport = learningReportRepo.findById(id);
        if (optionalReport.isPresent()) {
            LearningReportEntity report = optionalReport.get();
            // Remove objectives if needed
            report.removeObjectives(report.getObjectives());
            learningReportRepo.deleteById(id);
            return "Learning Report Id" + id + " has been deleted!";
        } else {
            return "Learning Report Id" + id + " not found!";
        }
    }
}
