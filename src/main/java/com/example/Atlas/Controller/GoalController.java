package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.GoalEntity;
import com.example.Atlas.Service.GoalService;

@RestController
@RequestMapping("/goals")
@CrossOrigin
public class GoalController {
    @Autowired
    GoalService goalserv;

    @PostMapping("/insert")
    public ResponseEntity<String> insertGoal(@RequestBody GoalEntity request) {
        try {
            GoalEntity insertedGoal = goalserv.insertGoal(request);
            return new ResponseEntity<>("New goal added with ID: " + insertedGoal.getId(), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getLatestGoalsByDepartmentId(@PathVariable int departmentId) {
        try {
            GoalEntity latestGoals = goalserv.getLatestGoalsByDepartmentId(departmentId);
            return ResponseEntity.ok(latestGoals);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No goals found for department id " + departmentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{departmentId}")
public ResponseEntity<String> updateGoalsById(@PathVariable int departmentId, @RequestBody GoalEntity request) {
    try {
        // Set the department ID from the path variable to the request object
        request.getDepartment().setId(departmentId);
        
        GoalEntity updatedGoal = goalserv.updateGoalsById(request);
        return new ResponseEntity<>("Goal updated with ID: " + updatedGoal.getId(), HttpStatus.OK);
    } catch (NoSuchElementException e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    
}
