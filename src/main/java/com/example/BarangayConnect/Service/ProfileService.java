package com.example.BarangayConnect.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarangayConnect.Entity.ProfileEntity;
import com.example.BarangayConnect.Repository.ProfileRepository;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository prepo;

    // Create
    public ProfileEntity insertProfile(ProfileEntity pentity) {
        return prepo.save(pentity);
    }

    // Read
    public List<ProfileEntity> getAllProfileInfo() {
        return prepo.findAll();
    }

    // Read by id
    public Optional<ProfileEntity> getProfileInfoById(int pid) {
        return prepo.findById(pid);
    }

    // Update
    @SuppressWarnings("finally")
    public ProfileEntity updateProfile(int pid, ProfileEntity newProfileDetails) {
        ProfileEntity profileInfo = new ProfileEntity();

        try {
            // search the id number of user/admin that will be updated
            profileInfo = prepo.findById(pid).get();

            profileInfo.setMobileNumber(newProfileDetails.getMobileNumber());
            profileInfo.setMaritalStatus(newProfileDetails.getMaritalStatus());
            profileInfo.setCitizenship(newProfileDetails.getCitizenship());
            profileInfo.setReligion(newProfileDetails.getReligion());
            prepo.save(profileInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + pid + " does not exist!");
        } finally {
            return prepo.save(profileInfo);
        }
    }

    // Delete
    public String deleteProfile(int pid) {
        String msg = "";
        if (prepo.findById(pid) != null) {
            prepo.deleteById(pid);
            msg = "User " + pid + " has been deleted!";
        } else {
            msg = "User " + pid + " does not exist!";
        }
        return msg;
    }
}
