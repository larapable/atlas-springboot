package com.example.Atlas.Repository;

import com.example.Atlas.Entity.stEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface stRepository extends JpaRepository<stEntity, Integer>{

    List<stEntity> findByDepartmentId(int departmentId);
    
}
