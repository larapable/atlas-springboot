package com.example.BarangayConnect.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BarangayConnect.Entity.UserEntity;
import com.example.BarangayConnect.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    // Create
    public UserEntity insertInfo(UserEntity lsentity) {
        if (userRepo.findByUsername(lsentity.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }
        return userRepo.save(lsentity);
    }

    // Add image
    public UserEntity addInfo(UserEntity userEntity) {
    UserEntity existingUser = userRepo.findById(userEntity.getId()).orElse(null);
    existingUser.setPhotoPath(userEntity.getPhotoPath());
    return userRepo.save(existingUser);
    }

    // Read
    public List<UserEntity> getAllInfo() {
        return userRepo.findAll();
    }

    // Read by id
    public Optional<UserEntity> getInfoById(int userId) {
        return userRepo.findById(userId);
    }

    // Read by username
    public UserEntity getInfoByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    // Update
    @SuppressWarnings("finally")
    public UserEntity updateInfo(int userId, UserEntity newLSDetails) {
        UserEntity accInfo = new UserEntity();

        try {
            // search the id number of user/admin that will be updated
            accInfo = userRepo.findById(userId).get();

            accInfo.setIsVerified(newLSDetails.isVerified());
            userRepo.save(accInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + userId + " does not exist!");
        } finally {
            return userRepo.save(accInfo);
        }
    }

    // Update by username
    @SuppressWarnings("finally")
    public UserEntity updateInfoByUsername(String username, UserEntity newLSDetails) {
        UserEntity accInfo = new UserEntity();

        try {
            // search the username of user/admin that will be updated
            accInfo = userRepo.findByUsername(username);

            accInfo.setPassword(newLSDetails.getPassword());
            accInfo.setMobileNumber(newLSDetails.getMobileNumber());
            accInfo.setMaritalStatus(newLSDetails.getMaritalStatus());
            accInfo.setCitizenship(newLSDetails.getCitizenship());
            accInfo.setReligion(newLSDetails.getReligion());
            userRepo.save(accInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + username + " does not exist!");
        } finally {
            return userRepo.save(accInfo);
        }
    }

    // Delete
    public String deleteInfo(int userId) {
        String msg = "";
        if (userRepo.findById(userId) != null) {
            userRepo.deleteById(userId);
            msg = "User " + userId + " has been deleted!";
        } else {
            msg = "User " + userId + " does not exist!";
        }
        return msg;
    }

    public UserEntity authenticateUser(String username, String password) {
        UserEntity user = userRepo.findByUsernameAndPassword(username, password);
        return user;
    }

    public void uploadImage(MultipartFile file) throws IOException {
        Path imagesPath = Paths.get("images");
        if (file.getOriginalFilename() == null) {
            throw new IOException("Original name is null");
        }
        String originalFilename = file.getOriginalFilename();
        Path filePath = imagesPath.resolve(originalFilename);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
