package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Atlas.Entity.GoalEntity;
import com.example.Atlas.Service.GoalService;

@RestController
@RequestMapping("/goals")
@CrossOrigin
public class GoalController {
    @Autowired
    GoalService goalserv;

    
    @PostMapping("/insert")
    public GoalEntity inserGoal(@RequestBody GoalEntity goal) {
        return goalserv.insertGoal(goal);
    }
    
}
