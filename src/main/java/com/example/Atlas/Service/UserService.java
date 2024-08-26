package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.UserEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userrepo;

    @Autowired
    DepartmentRepository departmentrepo;
    
     @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity insertUser(UserEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        request.setDepartment(department);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userrepo.save(request);
    }

    // Verify the password during login
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean checkEmailExists(String email) {
        return userrepo.existsByEmail(email);
    }

    public boolean checkUserNameExists(String username) {
        return userrepo.existsByUsername(username);
    }

    public UserEntity getUserByUsername(String username) {
        return userrepo.findByUsername(username);
    }

    // Get user by ID
    public UserEntity getUserById(int id) {
        Optional<UserEntity> UserOptional = userrepo.findById(id);
        return UserOptional.orElse(null);
    }
    

    public boolean updateUserDetails(
            int id,
            String firstname,
            String lastname,
            String role,
            String email,
            int age,
            Date birthdate

    ) {
        try {
            // Get the user entity by username
            Optional<UserEntity> userOptional = userrepo.findById(id);
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                // Update the user details
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setRole(role);
                user.setEmail(email);
                user.setAge(age);
                user.setBirthdate(birthdate);
                userrepo.save(user);
                return true; // User details successfully updated
            } else {
                return false; // No user found with the given username
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating user details.", e);
        }
    }



    public String updateUserGeneratedAiStrats(String username) {
       UserEntity user = userrepo.findByUsername(username);
        if (user == null) {
            return "User not found";
        }

        if (user.getGeneratedAiStrats() == 0) {
            user.setGeneratedAiStrats(1);
            userrepo.save(user);
            return "User updated successfully";
        }

        return "User already has generatedAiStrats set";
    }

    public int getUserCount() {
        return (int) userrepo.count();
    }

    public List<UserEntity> getAllUsers() {
        return userrepo.findAll();
    }
}
