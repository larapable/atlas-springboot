package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BarangayConnect.Entity.BusinessEntity;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity, Integer>{
    
}
