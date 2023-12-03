package com.example.BarangayConnect.Service;

import com.example.BarangayConnect.Entity.EmergencyEntity;
import com.example.BarangayConnect.Entity.UserEntity;
import com.example.BarangayConnect.Repository.EmergencyRepository;
import com.example.BarangayConnect.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmergencyService {

    @Autowired
    EmergencyRepository emergencyRepository;

    @Autowired
    UserRepository userRepository;

    public EmergencyEntity insertEmergency(EmergencyEntity emergencyEntity) {
        UserEntity user = userRepository.findById(emergencyEntity.getUser().getId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        emergencyEntity.setUser(user);
        return emergencyRepository.save(emergencyEntity);
    }

    public List<EmergencyEntity> getAllEmergency() {
        return emergencyRepository.findAll();
    }

    @SuppressWarnings("finally")
    public EmergencyEntity updateEmergency(int emergencyId, EmergencyEntity newEmergencyDetails) {
        EmergencyEntity emergency = new EmergencyEntity();
        try {
            emergency = emergencyRepository.findById(emergencyId)
                    .orElseThrow(() -> new NoSuchElementException("Emergency " + emergencyId + " not found"));

            emergency.setUser(newEmergencyDetails.getUser());
            emergency.setTypeOfIncident(newEmergencyDetails.getTypeOfIncident());
            emergency.setDate(newEmergencyDetails.getDate());
            emergency.setTime(newEmergencyDetails.getTime());
            emergency.setExactLocation(newEmergencyDetails.getExactLocation());
            emergency.setIncidentDetails(newEmergencyDetails.getIncidentDetails());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Emergency " + emergencyId + " does not exist!");
        } finally {
            return emergencyRepository.save(emergency);
        }
    }

    //Delete 
    public String deleteEmergency(int emergencyId) {
        String msg="";
        if (emergencyRepository.findById(emergencyId) != null) {
            emergencyRepository.deleteById(emergencyId);
            msg = "Request "+emergencyId+" is succesfully deleted!";
        } else 
            msg = "Request "+emergencyId+" does not exist!";
            return msg;
    } 
}
