package com.example.Atlas.Repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Atlas.Entity.GoalEntity;
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {
    
}
