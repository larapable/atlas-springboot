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
    UserRepository lsrepo;

    // Create
    public UserEntity insertInfo(UserEntity lsentity) {
        if (lsrepo.findByUsername(lsentity.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }
        return lsrepo.save(lsentity);
    }

    // Add image
    public UserEntity addInfo(UserEntity userEntity) {
        UserEntity existingUser = lsrepo.findById(userEntity.getId()).orElse(null);
        existingUser.setPhotoPath(userEntity.getPhotoPath());
        return lsrepo.save(existingUser);
    }

    // Read
    public List<UserEntity> getAllInfo() {
        return lsrepo.findAll();
    }

    // Read by id
    public Optional<UserEntity> getInfoById(int userId) {
        return lsrepo.findById(userId);
    }

    // Read by username
    public UserEntity getInfoByUsername(String username) {
        return lsrepo.findByUsername(username);
    }

    // Update
    @SuppressWarnings("finally")
    public UserEntity updateInfo(int userId, UserEntity newLSDetails) {
        UserEntity accInfo = new UserEntity();

        try {
            // search the id number of user/admin that will be updated
            accInfo = lsrepo.findById(userId).get();

            accInfo.setUsername(newLSDetails.getUsername());
            accInfo.setPassword(newLSDetails.getPassword());
            accInfo.setEmail(newLSDetails.getEmail());
            accInfo.setFname(newLSDetails.getFname());
            accInfo.setLname(newLSDetails.getLname());
            accInfo.setAddress(newLSDetails.getAddress());
            accInfo.setDateOfBirth(newLSDetails.getDateOfBirth());
            accInfo.setGender(newLSDetails.getGender());
            accInfo.setMobileNumber(newLSDetails.getMobileNumber());
            accInfo.setMaritalStatus(newLSDetails.getMaritalStatus());
            accInfo.setCitizenship(newLSDetails.getCitizenship());
            accInfo.setReligion(newLSDetails.getReligion());
            accInfo.setPhotoPath(newLSDetails.getPhotoPath());
            lsrepo.save(accInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + userId + " does not exist!");
        } finally {
            return lsrepo.save(accInfo);
        }
    }

    // Update by username
    @SuppressWarnings("finally")
    public UserEntity updateInfoByUsername(String username, UserEntity newLSDetails) {
        UserEntity accInfo = new UserEntity();

        try {
            // search the username of user/admin that will be updated
            accInfo = lsrepo.findByUsername(username);

            accInfo.setMobileNumber(newLSDetails.getMobileNumber());
            accInfo.setMaritalStatus(newLSDetails.getMaritalStatus());
            accInfo.setCitizenship(newLSDetails.getCitizenship());
            accInfo.setReligion(newLSDetails.getReligion());
            lsrepo.save(accInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + username + " does not exist!");
        } finally {
            return lsrepo.save(accInfo);
        }
    }

    // Delete
    public String deleteInfo(int userId) {
        String msg = "";
        if (lsrepo.findById(userId) != null) {
            lsrepo.deleteById(userId);
            msg = "User " + userId + " has been deleted!";
        } else {
            msg = "User " + userId + " does not exist!";
        }
        return msg;
    }

    public UserEntity authenticateUser(String username, String password) {
        UserEntity user = lsrepo.findByUsernameAndPassword(username, password);

        // Check if the user is not null (found) and is verified
        return user;// != null && user.isVerified();
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
