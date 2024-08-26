package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.InternalReportEntity;

public interface InternalReportRepository extends JpaRepository<InternalReportEntity, Integer> {
    List<InternalReportEntity> findByDepartmentId(int departmentId);
}
