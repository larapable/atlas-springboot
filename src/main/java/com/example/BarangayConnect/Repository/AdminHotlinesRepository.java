package com.example.BarangayConnect.Repository;

import com.example.BarangayConnect.Entity.AdminHotlinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminHotlinesRepository extends JpaRepository<AdminHotlinesEntity, Integer> {
}
