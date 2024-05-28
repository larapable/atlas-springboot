package com.example.Atlas.Repository;

import com.example.Atlas.Entity.soEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface soRepository extends JpaRepository<soEntity, Integer>{

    List<soEntity> findByDepartmentId(int departmentId);
    
}
