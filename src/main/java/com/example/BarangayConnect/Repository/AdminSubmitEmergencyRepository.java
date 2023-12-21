package com.example.BarangayConnect.Repository;

import com.example.BarangayConnect.Entity.AdminSubmitEmergencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminSubmitEmergencyRepository extends JpaRepository<AdminSubmitEmergencyEntity, Integer> {

}
