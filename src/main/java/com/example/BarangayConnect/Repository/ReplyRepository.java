package com.example.BarangayConnect.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BarangayConnect.Entity.ForumEntity;
import com.example.BarangayConnect.Entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Integer>{
 
   List<ReplyEntity> findByForum(ForumEntity forum);
   
}
