package com.example.Atlas.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "financial_bsc")
public class FinancialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String target_code;

    private String office_target;

    private LocalDate startDate;
    
    private LocalDate completionDate;

    private String status;

    private String key_performance_indicator;

    private Float target_performance;

    private Float actual_performance;

    @ManyToOne()
    @JoinColumn(name = "department_id") 
    private DepartmentEntity department;

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public String getOffice_target() {
        return office_target;
    }

    public void setOffice_target(String office_target) {
        this.office_target = office_target;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey_performance_indicator() {
        return key_performance_indicator;
    }

    public void setKey_performance_indicator (String key_performance_indicator) {
        this.key_performance_indicator = key_performance_indicator;
    }

    public Float getTarget_performance() {
        return target_performance;
    }

    public void setTarget_performance(Float target_performance) {
        this.target_performance = target_performance;
    }

    public Float getActual_performance() {
        return actual_performance;
    }

    public void setActual_performance(Float actual_performance) {
        this.actual_performance = actual_performance;
    }

        // Getters and setters for department
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    
}
