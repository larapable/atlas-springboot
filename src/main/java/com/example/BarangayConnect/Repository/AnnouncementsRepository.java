package com.example.BarangayConnect.Repository;

import com.example.BarangayConnect.Entity.AnnouncementsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementsRepository extends JpaRepository<AnnouncementsEntity, Integer> {
    
}
