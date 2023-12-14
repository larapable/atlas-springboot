package com.example.BarangayConnect.Controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.BarangayConnect.Entity.UserEntity;
import com.example.BarangayConnect.Repository.UserRepository;
import com.example.BarangayConnect.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login-signup")
public class UserController {
    @Autowired
    UserService uservice;
    @Autowired
    UserRepository urepo;

    // Create
    @PostMapping("addInfo")
    public UserEntity insertInfo(@RequestBody UserEntity lsentity) {
        return uservice.insertInfo(lsentity);
    }

    // Add Imagepath to tblAccount using username
    @PostMapping("/uploadImage/{username}")
    public UserEntity insertImage(
            @PathVariable String username,
            @RequestParam("image") MultipartFile image) throws IOException {
        uservice.uploadImage(image);
        UserEntity loginSignupEntity = uservice.getInfoByUsername(username);
        loginSignupEntity.setPhotoPath(image.getOriginalFilename());
        return uservice.addInfo(loginSignupEntity);
    }

    // Read all
    @GetMapping("getAllUsers")
    public List<UserEntity> getAllInfo() {
        return uservice.getAllInfo();
    }

    // Read by id
    @GetMapping("getInfoById/{userId}")
    public Optional<UserEntity> getInfoById(@PathVariable int userId) {
        return uservice.getInfoById(userId);
    }

    // Read by username
    @GetMapping("getInfoByUsername/{username}")
    public UserEntity getInfoByUsername(@PathVariable String username) {
        return uservice.getInfoByUsername(username);
    }

    // Get image from tblAccount using username
    @GetMapping("/getUserImage/{username}")
    public ResponseEntity<String> getUserImage(@PathVariable String username) {
        try {
            // Retrieve user information by username
            UserEntity userEntity = uservice.getInfoByUsername(username);

            if (userEntity != null && userEntity.getPhotoPath() != null) {
                // If the user has a photoPath, return it
                return ResponseEntity.ok(userEntity.getPhotoPath());
            } else {
                // If the user or photoPath is not found, return a not found status
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle exceptions and return an internal server error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update by id
    @PutMapping("/updateInfo/{userId}")
    public UserEntity updateInfo(@PathVariable int userId, @RequestBody UserEntity newLSDetails) {
        return uservice.updateInfo(userId, newLSDetails);
    }

    // Update by username
    @PutMapping("/updateUserInfo/{username}")
    public ResponseEntity<String> updateUserInfo(@PathVariable String username,
            @RequestBody UserEntity newUserInfo) {
        try {
            uservice.updateInfoByUsername(username, newUserInfo);
            return ResponseEntity.ok("User info updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete
    @DeleteMapping("deleteInfo/{userId}")
    public String deleteInfo(@PathVariable int userId) {
        return uservice.deleteInfo(userId);
    }

    // D - Delete
    @PutMapping("/delete/{userId}")
    public ResponseEntity<java.util.Map<String, String>> deleteUser(@PathVariable int userId) {
        java.util.Map<String, String> response = new HashMap<>();
        Optional<UserEntity> userOptional = urepo.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setIsDeleted(true); // Set isDeleted to true instead of deleting the record
            urepo.save(user); // Save the updated user record
            response.put("message", "User " + userId + " is successfully deleted");
        } else {
            response.put("message", "User " + userId + " does not exist");
        }
        return ResponseEntity.ok(response);
    }

    // Authentication
    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody UserEntity request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Your authentication logic here
        UserEntity authUser = uservice.authenticateUser(username, password);

        if (authUser.isVerified()) {
            return ResponseEntity.ok().body(authUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}