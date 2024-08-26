package com.example.Atlas.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.StakeholderReportEntity;

public interface StakeholderReportRepository extends JpaRepository<StakeholderReportEntity, Integer> {
    List<StakeholderReportEntity> findByDepartmentId(int departmentId);
}
