package com.example.BarangayConnect.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.LoginSignupEntity;
import com.example.BarangayConnect.Service.LoginSignupService;

@RestController
@RequestMapping("/login-signup")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginSignupController {
    @Autowired
    LoginSignupService lsservice;

    // Create
    @PostMapping("addInfo")
    public LoginSignupEntity insertInfo(@RequestBody LoginSignupEntity lsentity) {
        return lsservice.insertInfo(lsentity);
    }

    // Read
    @GetMapping("getAllInfo")
    public List<LoginSignupEntity> getAllInfo() {
        return lsservice.getAllInfo();
    }
    // Read by id
    @GetMapping("getInfoById/{userId}")
    public Optional<LoginSignupEntity> getInfoById(@PathVariable int userId) {
        return lsservice.getInfoById(userId);
    }
    // Read by username
    @GetMapping("getInfoByUsername/{username}")
    public LoginSignupEntity getInfoByUsername(@PathVariable String username) {
        return lsservice.getInfoByUsername(username);
    }

    // Update
    @PutMapping("updateInfo")
    public LoginSignupEntity updateInfo(@RequestParam int userId, @RequestBody LoginSignupEntity newLSDetails) {
        return lsservice.updateInfo(userId, newLSDetails);
    }

    // Delete
    @DeleteMapping("deleteInfo/{userId}")
    public String deleteInfo(@PathVariable int userId) {
        return lsservice.deleteInfo(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginSignupEntity request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Your authentication logic here
        boolean isAuthenticated = lsservice.authenticateUser(username, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
