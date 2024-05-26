package com.example.Atlas.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.GoalEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.GoalRepository;

@Service
public class GoalService {
      @Autowired
      GoalRepository goalrepo;

      @Autowired
      DepartmentRepository departmentrepo;

       public GoalEntity insertGoal(GoalEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        request.setDepartment(department);
        return goalrepo.save(request);
    }

    
}
