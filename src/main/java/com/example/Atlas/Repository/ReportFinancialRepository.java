package com.example.Atlas.Repository;

import com.example.Atlas.Entity.ReportFinancialEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportFinancialRepository extends JpaRepository<ReportFinancialEntity, Integer> {
    List<ReportFinancialEntity> findByDepartmentId(int departmentId);
}
