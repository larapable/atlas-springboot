package com.example.Atlas.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "weakness")
public class WeaknessEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    private boolean isDelete = false;  // Add this line

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


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

     // Add getter and setter for isDelete
     public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

     // Getters and setters for department
     public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
