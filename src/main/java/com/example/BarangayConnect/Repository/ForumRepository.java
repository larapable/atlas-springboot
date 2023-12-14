package com.example.BarangayConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BarangayConnect.Entity.ForumEntity;

public interface ForumRepository  extends JpaRepository<ForumEntity,Integer> {
    
}
