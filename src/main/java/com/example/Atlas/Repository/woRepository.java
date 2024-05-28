package com.example.Atlas.Repository;

import com.example.Atlas.Entity.woEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface woRepository extends JpaRepository<woEntity, Integer>{
    
    List<woEntity> findByDepartmentId(int departmentId);
}
