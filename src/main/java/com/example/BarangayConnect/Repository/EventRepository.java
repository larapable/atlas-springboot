package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Integer>{
    
}
