package com.example.Atlas.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.FinancialReportEntity;

public interface FinancialReportRepository extends JpaRepository<FinancialReportEntity, Integer> {
    List<FinancialReportEntity> findByDepartmentId(int departmentId);
}
