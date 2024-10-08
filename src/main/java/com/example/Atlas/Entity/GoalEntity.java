package com.example.Atlas.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goal")
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vision;

    private String proposition;

    private String mission;

    private String goals;

    private String goals2;

    private String goals3;

    private int targetYear;

    private boolean accomplished;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // Specify the foreign key column
    private DepartmentEntity department;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getProposition() {
        return proposition;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getMission() {
        return mission;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public boolean isAccomplished() {
        return accomplished;
    }

    public void setAccomplished(boolean accomplished) {
        this.accomplished = accomplished;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoals2() {
        return goals2;
    }

    public void setGoals2(String goals2) {
        this.goals2 = goals2;
    }

    public String getGoals3() {
        return goals3;
    }

    public void setGoals3(String goals3) {
        this.goals3 = goals3;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}