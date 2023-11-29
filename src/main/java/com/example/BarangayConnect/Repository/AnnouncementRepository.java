package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.AnnouncementEntity;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer>{
}