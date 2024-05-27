package com.example.Atlas.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.GoalEntity;
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

    Optional<GoalEntity> findTopByDepartmentIdOrderByIdDesc(int departmentId);
}
