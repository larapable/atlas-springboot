package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BarangayConnect.Entity.AdminDirectoryListEntity;

@Repository
public interface AdminDirectoryListRepository extends JpaRepository<AdminDirectoryListEntity, Integer>{
    
}
