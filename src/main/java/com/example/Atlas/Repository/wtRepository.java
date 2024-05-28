package com.example.Atlas.Repository;

import com.example.Atlas.Entity.wtEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface wtRepository extends JpaRepository<wtEntity, Integer> {
    
     List<wtEntity> findByDepartmentId(int departmentId);
}
