package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
import java.util.Map;
import java.util.HashMap;
import java.util.List;

>>>>>>> d97fcbf946986816f2fcdf25dd548a4c82270917
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.UserEntity;
import com.example.Atlas.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userserv;

    @PostMapping("/userExists")
    public Map<String, Boolean> checkUserExists(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String username = request.get("username");

        boolean emailExists = userserv.checkEmailExists(email);
        boolean userNameExists = userserv.checkUserNameExists(username);

        Map<String, Boolean> response = new HashMap<>();
        response.put("userEmail", emailExists);
        response.put("userName", userNameExists);

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        UserEntity user = userserv.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        boolean passwordMatch = userserv.checkPassword(password, user.getPassword());
        if (!passwordMatch) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/insert")
    public UserEntity insertUser(@RequestBody UserEntity user) {
        return userserv.insertUser(user);
    }

    
    @GetMapping("/{userId}")
    public UserEntity getProfileData(@PathVariable int userId) {
        return userserv.getUserById(userId);
    }

    @GetMapping("get/{username}")
    public UserEntity getUser(@PathVariable String username) {
        return userserv.getUserByUsername(username);
    }
<<<<<<< HEAD
    
  
=======

>>>>>>> d97fcbf946986816f2fcdf25dd548a4c82270917
    @PutMapping("/update/{username}")
    public String updateUser(@PathVariable String username) {
        return userserv.updateUserGeneratedAiStrats(username);
    }

<<<<<<< HEAD
    @PutMapping("/update/profile/{user_id}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable("user_id") int user_id,
            @RequestBody UserEntity request) {
        try {
            boolean success = userserv.updateUserDetails(
                   user_id,
                   request.getFirstname(),
                   request.getLastname(),
                   request.getRole(),
                   request.getEmail(),
                   request.getAge(),
                   request.getBirthdate()
                   
            );
            if (success) {
                return ResponseEntity.ok("User profile updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + user_id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user profile: " + e.getMessage());
        }
    }

  
    
   
=======
    @GetMapping("/userCount")
    public Map<String, Integer> getUserCount() {
        Map<String, Integer> response = new HashMap<>();
        Integer userCount = userserv.getUserCount();
        response.put("userCount", userCount);
        return response;
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userserv.getAllUsers();
    }
>>>>>>> d97fcbf946986816f2fcdf25dd548a4c82270917
}
