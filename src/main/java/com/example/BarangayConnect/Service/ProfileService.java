package com.example.BarangayConnect.Service;

import java.util.*;

import javax.persistence.StoredProcedureParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
    public List<ProfileEntity> getAllProfile() {
        return prepo.findAll();
    }

    // Update
    @SuppressWarnings("finally")
    public ProfileEntity updateProfile(int id, ProfileEntity newProfileDetails) {
        ProfileEntity profileInfo = new ProfileEntity();

        try {
            // search the id number of user/admin that will be updated
            profileInfo = prepo.findById(id).get();

            profileInfo.setMobileNumber(newProfileDetails.getMobileNumber());
            profileInfo.setMaritalStatus(newProfileDetails.getMaritalStatus());
            profileInfo.setCitizenship(newProfileDetails.getCitizenship());
            profileInfo.setReligion(newProfileDetails.getReligion());
            prepo.save(profileInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User " + id + " does not exist!");
        } finally {
            return prepo.save(profileInfo);
        }
    }

    // Delete
    public String deleteProfile(int id) {
        String msg = "";
        if (prepo.findById(id) != null) {
            prepo.deleteById(id);
            msg = "User " + id + " has been deleted!";
        } else {
            msg = "User " + id + " does not exist!";
        }
        return msg;
    }
}
