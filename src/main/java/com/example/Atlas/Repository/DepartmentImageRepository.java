package com.example.Atlas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Atlas.Entity.DepartmentImageEntity;

@Repository
public interface DepartmentImageRepository extends JpaRepository<DepartmentImageEntity, Integer> {
    Optional<DepartmentImageEntity> findByDepartmentId(int departmentId);
    boolean existsByDepartmentId(int departmentId);
}
