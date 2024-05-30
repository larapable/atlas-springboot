package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.UserEntity;
import com.example.Atlas.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userserv;

    @PostMapping("/userExists")
    public Map<String, Boolean> checkUserExists(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String username = request.get("username");

        boolean userExists = userserv.checkUserExists(email, username);
        boolean userNameExists = userserv.checkUserNameExists(username);

        Map<String, Boolean> response = new HashMap<>();
        response.put("user", userExists);
        response.put("userName", userNameExists);

        return response;
    }

    @PostMapping("/insert")
    public UserEntity insertUser(@RequestBody UserEntity user) {
        return userserv.insertUser(user);
    }

    @GetMapping("get/{username}")
    public UserEntity getUser(@PathVariable String username) {
        return userserv.getUserByUsername(username);
    }

  
    @PutMapping("/update/{username}")
    public String updateUser(@PathVariable String username) {
        return userserv.updateUserGeneratedAiStrats(username);
    }
  
    
   
}
