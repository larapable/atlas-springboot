package com.example.Atlas.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;




@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentrepo;
       
    //Post department
      public DepartmentEntity department_register(DepartmentEntity department) {
        try {
            return departmentrepo.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Department already exists with the provided details!");
        }
    }
    // Read / display all the department details of the table 
    public List<DepartmentEntity> getAllDepartment() {
        return departmentrepo.findAll();
    }

    
    // Get department by ID
    public DepartmentEntity getDepartmentById(int id) {
        Optional<DepartmentEntity> departmentOptional = departmentrepo.findById(id);
        return departmentOptional.orElse(null);
    }

     public boolean updateDepartmentDetails(
            int id,
            String head_officer,
            String department_landline,
            String location,
            String university,
            String description
           
    ) {
        try {
            // Get the department entity by ID
            Optional<DepartmentEntity> departmentOptional = departmentrepo.findById(id);
            if (departmentOptional.isPresent()) {
                DepartmentEntity department = departmentOptional.get();
                // Update the department details
                department.setHead_officer(head_officer);
                department.setDepartment_landline(department_landline);
                department.setLocation(location);
                department.setUniversity(university);
                department.setDescription(description);
                departmentrepo.save(department);
                return true; // Department details successfully updated
            } else {
                return false; // No department found with the given ID
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating department details.", e);
        }
    }





}
