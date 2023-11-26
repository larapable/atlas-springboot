package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>{
   
}
