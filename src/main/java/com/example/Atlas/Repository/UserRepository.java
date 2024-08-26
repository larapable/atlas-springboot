package com.example.Atlas.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

<<<<<<< HEAD
    <Optional> UserEntity findByUsername(String username);
=======
    UserEntity findByUsername(String username);

>>>>>>> d97fcbf946986816f2fcdf25dd548a4c82270917
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
