package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
}
