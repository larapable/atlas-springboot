package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.ThreatEntity;
public interface ThreatRepository extends JpaRepository<ThreatEntity, Integer>  {


     List<ThreatEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
     List<ThreatEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);
    
    
}
