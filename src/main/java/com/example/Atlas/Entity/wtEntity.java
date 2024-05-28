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
@Table(name = "w_tstrat")
public class wtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "w_tResponses")
    private String w_tResponses;

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

    
    public String getW_tResponses() {
        return w_tResponses;
    }

    public void setW_tResponses(String w_tResponses) {
        this.w_tResponses = w_tResponses;
    }

     // Getters and setters for department
     public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    
}
