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
    UserService lsservice;
    @Autowired
    UserRepository urepo;

    // Create
    @PostMapping("addInfo")
    public UserEntity insertInfo(@RequestBody UserEntity lsentity) {
        return lsservice.insertInfo(lsentity);
    }

    // Add Image to tblAccount using username
    @PostMapping("/uploadImage/{username}")
    public UserEntity insertImage(
            @PathVariable String username,
            @RequestParam("image") MultipartFile image) throws IOException {
        lsservice.uploadImage(image);
        UserEntity loginSignupEntity = lsservice.getInfoByUsername(username);
        loginSignupEntity.setPhotoPath(image.getOriginalFilename());
        return lsservice.addInfo(loginSignupEntity);
    }

    // Get image from tblAccount using username
    @GetMapping("/getUserImage/{username}")
    public ResponseEntity<String> getUserImage(@PathVariable String username) {
        try {
            // Retrieve user information by username
            UserEntity userEntity = lsservice.getInfoByUsername(username);

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

    // Read all
    @GetMapping("getAllInfo")
    public List<UserEntity> getAllInfo() {
        return lsservice.getAllInfo();
    }

    // Read by id
    @GetMapping("getInfoById/{userId}")
    public Optional<UserEntity> getInfoById(@PathVariable int userId) {
        return lsservice.getInfoById(userId);
    }

    // Read by username
    @GetMapping("getInfoByUsername/{username}")
    public UserEntity getInfoByUsername(@PathVariable String username) {
        return lsservice.getInfoByUsername(username);
    }

    // Update by id
    @PutMapping("updateInfo")
    public UserEntity updateInfo(@RequestParam int userId, @RequestBody UserEntity newLSDetails) {
        return lsservice.updateInfo(userId, newLSDetails);
    }

    // Update by username
    @PutMapping("/updateUserInfo/{username}")
    public ResponseEntity<String> updateUserInfo(@PathVariable String username,
            @RequestBody UserEntity newUserInfo) {
        try {
            lsservice.updateInfoByUsername(username, newUserInfo);
            return ResponseEntity.ok("User info updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete
    @DeleteMapping("deleteInfo/{userId}")
    public String deleteInfo(@PathVariable int userId) {
        return lsservice.deleteInfo(userId);
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
    public ResponseEntity<String> authenticateUser(@RequestBody UserEntity request) {
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

    // @PutMapping("/uploadImage/{username}")
    // public void uploadImage(@RequestParam("username") String username,
    // @RequestParam("file") MultipartFile image) {
    // lsservice.uploadImage(username, image);
    // }

}
