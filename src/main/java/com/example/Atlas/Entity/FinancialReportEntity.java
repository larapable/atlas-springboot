package com.example.Atlas.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "financial_report")
public class FinancialReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "dateCreated")
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "department_id") // Specify the foreign key column
    private DepartmentEntity department;

    @Column(name = "objectives", columnDefinition = "TEXT")
    private String objectives;

    public FinancialReportEntity() {
    }

    public FinancialReportEntity(String title, String description, LocalDate dateCreated, DepartmentEntity department,
            String objectives) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.department = department;
        this.objectives = objectives;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }
}
