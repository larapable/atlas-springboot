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
                DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                request.setDepartment(department);
                return goalrepo.save(request);
        }

        public GoalEntity getLatestGoalsByDepartmentId(int departmentId) {
                DepartmentEntity department = departmentrepo.findById(departmentId)
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                return goalrepo.findTopByDepartmentIdOrderByIdDesc(department.getId())
                                .orElseThrow(() -> new NoSuchElementException(
                                                "No goals found for department id " + departmentId));
        }

        public GoalEntity updateGoalsById(GoalEntity request) {
                DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                GoalEntity goal = goalrepo.findById(department.getId())
                                .orElseThrow(() -> new NoSuchElementException(
                                                "Goal not found with id " + department.getId()));

                goal.setVision(request.getVision());
                goal.setProposition(request.getProposition());
                goal.setGoals(request.getGoals());
                goal.setGoals2(request.getGoals2());
                goal.setGoals3(request.getGoals3());
                goal.setStartDate(request.getStartDate());
                goal.setEndDate(request.getEndDate());

                return goalrepo.save(goal);
        }

    }
    
    

