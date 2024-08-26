package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.OpportunityEntity;



public interface OpportunityRepository extends JpaRepository<OpportunityEntity, Integer> {

     List<OpportunityEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
     List<OpportunityEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);
}

