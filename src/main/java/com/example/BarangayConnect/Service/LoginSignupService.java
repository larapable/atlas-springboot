package com.example.BarangayConnect.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarangayConnect.Entity.LoginSignupEntity;
import com.example.BarangayConnect.Repository.LoginSignupRepository;

@Service
public class LoginSignupService {

    @Autowired
    LoginSignupRepository lsrepo;

    // Create
    public LoginSignupEntity insertInfo(LoginSignupEntity lsentity) {
        if (lsrepo.findByUsername(lsentity.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }
        return lsrepo.save(lsentity);
    }

    // Read
    public List<LoginSignupEntity> getAllInfo() {
        return lsrepo.findAll();
    }

    // Read by id
    public Optional<LoginSignupEntity> getInfoById(int userId) {
        return lsrepo.findById(userId);
    }

    // Read by username
    public LoginSignupEntity getInfoByUsername(String username) {
        return lsrepo.findByUsername(username);
    }

    // Update
    @SuppressWarnings("finally")
    public LoginSignupEntity updateInfo(int userId, LoginSignupEntity newLSDetails) {
        LoginSignupEntity accInfo = new LoginSignupEntity();

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
            lsrepo.save(accInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + userId + " does not exist!");
        } finally {
            return lsrepo.save(accInfo);
        }
    }

    @SuppressWarnings("finally")
    // Update by username
    public LoginSignupEntity updateInfoByUsername(String username, LoginSignupEntity newLSDetails) {
        LoginSignupEntity accInfo = new LoginSignupEntity();

        try {
            // search the username of user/admin that will be updated
            accInfo = lsrepo.findByUsername(username);

            //accInfo.setUsername(newLSDetails.getUsername());
            //accInfo.setPassword(newLSDetails.getPassword());
            //accInfo.setEmail(newLSDetails.getEmail());
            //accInfo.setFname(newLSDetails.getFname());
            //accInfo.setLname(newLSDetails.getLname());
            //accInfo.setAddress(newLSDetails.getAddress());
            //accInfo.setDateOfBirth(newLSDetails.getDateOfBirth());
            //accInfo.setGender(newLSDetails.getGender());
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

    public boolean authenticateUser(String username, String password) {
        LoginSignupEntity user = lsrepo.findByUsernameAndPassword(username, password);

        // Check if the user is not null (found) and is verified
        return user != null && user.isVerified();
    }

}
