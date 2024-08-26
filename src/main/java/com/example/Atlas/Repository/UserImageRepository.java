package com.example.Atlas.Repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Atlas.Entity.UserImageEntity;

@Repository
public interface UserImageRepository extends JpaRepository<UserImageEntity, Integer> {
    Optional<UserImageEntity> findByUserId(int userId);
    boolean existsByUserId(int userId);
}
