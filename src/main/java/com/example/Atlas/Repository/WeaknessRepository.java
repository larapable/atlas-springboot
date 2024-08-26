package com.example.Atlas.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Atlas.Entity.WeaknessEntity;
public interface WeaknessRepository extends JpaRepository<WeaknessEntity, Integer>{

    List<WeaknessEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
    List<WeaknessEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);
}
