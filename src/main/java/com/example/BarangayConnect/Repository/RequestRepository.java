package com.example.BarangayConnect.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity,Integer> {
}
