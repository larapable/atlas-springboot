package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByUsernameAndPassword(String username, String password);
}
