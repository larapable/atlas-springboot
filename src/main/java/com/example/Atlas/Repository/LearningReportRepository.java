package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.LearningReportEntity;

public interface LearningReportRepository extends JpaRepository<LearningReportEntity, Integer> {
    List<LearningReportEntity> findByDepartmentId(int departmentId);
}
