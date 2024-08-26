package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.ReportFinancialEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.ReportFinancialRepository;

@Service
public class ReportFinancialService {
    @Autowired
    ReportFinancialRepository reportfinancialrepo;
    @Autowired
    DepartmentRepository departmentRepo;

    // Get all financial reports
    public List<ReportFinancialEntity> getAllFinancialReports() {
        List<ReportFinancialEntity> reports = reportfinancialrepo.findAll();
        if (reports.isEmpty()) {
            throw new NoSuchElementException("No financial reports found.");
        }
        return reports;
    }

    // Get all financial reports by department id
    public List<ReportFinancialEntity> getFinancialReportsByDepartmentId(int departmentId) {
        return reportfinancialrepo.findByDepartmentId(departmentId);
    }

    // Insert a new financial report
    public ReportFinancialEntity insertFinancialReport(int departmentId, ReportFinancialEntity request) {
        try {
            DepartmentEntity department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new NoSuchElementException("Department not found for id: " + departmentId));

            request.setDepartment(department);

            return reportfinancialrepo.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert financial report.", e);
        }
    }

    // Update an existing financial report
    public ReportFinancialEntity updateFinancialReport(int reportId, ReportFinancialEntity updatedReport) {
        try {
            ReportFinancialEntity existingReport = reportfinancialrepo.findById(reportId)
                    .orElseThrow(() -> new NoSuchElementException("Report not found for id: " + reportId));

            existingReport.setActions(updatedReport.getActions());
            existingReport.setSemester(updatedReport.getSemester());
            existingReport.setBudget(updatedReport.getBudget());
            existingReport.setIncharge(updatedReport.getIncharge());
            existingReport.setOfi(updatedReport.getOfi());
            // Add other fields to be updated as needed

            return reportfinancialrepo.save(existingReport);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update financial report.", e);
        }
    }

}
