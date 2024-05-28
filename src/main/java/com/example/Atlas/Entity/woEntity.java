package com.example.Atlas.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "w_ostrat")
public class woEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "w_oResponses")
    private String w_oResponses;

    @ManyToOne()
    @JoinColumn(name = "department_id") // Specify the foreign key column
    private DepartmentEntity department;


     // Getters and setters for id
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getW_oResponses() {
        return w_oResponses;
    }

    public void setW_oResponses(String w_oResponses) {
        this.w_oResponses = w_oResponses;
    }

     // Getters and setters for department
     public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    
}
