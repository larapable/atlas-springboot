package com.example.BarangayConnect.Repository;

import com.example.BarangayConnect.Entity.EmergencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRepository extends JpaRepository<EmergencyEntity, Integer> {

}
