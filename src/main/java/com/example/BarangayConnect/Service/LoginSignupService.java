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
        return lsrepo.save(lsentity);
    }

    // Read
    public List<LoginSignupEntity> getAllInfo() {
        return lsrepo.findAll();
    }

    // Update
    @SuppressWarnings("finally")
    public LoginSignupEntity updateInfo(int id, LoginSignupEntity newLSDetails) {
        LoginSignupEntity accInfo = new LoginSignupEntity();

        try {
            // search the id number of user/admin that will be updated
            accInfo = lsrepo.findById(id).get();

            accInfo.setUsername(newLSDetails.getUsername());
            accInfo.setPassword(newLSDetails.getPassword());
            accInfo.setEmail(newLSDetails.getEmail());
            accInfo.setFname(newLSDetails.getFname());
            accInfo.setLname(newLSDetails.getLname());
            accInfo.setAddress(newLSDetails.getAddress());
            accInfo.setDateOfBirth(newLSDetails.getDateOfBirth());
            accInfo.setGender(newLSDetails.getGender());
            lsrepo.save(accInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + id + " does not exist!");
        } finally {
            return lsrepo.save(accInfo);
        }
    }

    // Delete
    public String deleteInfo(int id) {
        String msg = "";
        if (lsrepo.findById(id) != null) {
            lsrepo.deleteById(id);
            msg = "User " + id + " has been deleted!";
        } else {
            msg = "User " + id + " does not exist!";
        }
        return msg;
    }

    public boolean authenticateUser(String username, String password) {
        LoginSignupEntity user = lsrepo.findByUsernameAndPassword(username, password);

        // Check if the user is not null (found) and is verified
        return user != null && user.isVerified();
    }

}
