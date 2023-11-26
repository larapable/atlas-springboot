package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.LoginSignupEntity;

public interface LoginSignupRepository extends JpaRepository<LoginSignupEntity, Integer>{
    LoginSignupEntity findByUsernameAndPassword(String username, String password);
    LoginSignupEntity findByUsername(String username);
}
