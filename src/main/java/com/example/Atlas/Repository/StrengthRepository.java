package com.example.Atlas.Repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Atlas.Entity.StrengthEntity;

public interface StrengthRepository extends JpaRepository<StrengthEntity, Integer> {

    List<StrengthEntity> findByDepartmentId(int departmentId);
}
