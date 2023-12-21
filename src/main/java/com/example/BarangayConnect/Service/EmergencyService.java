package com.example.BarangayConnect.Service;

import com.example.BarangayConnect.Entity.EmergencyEntity;
import com.example.BarangayConnect.Entity.UserEntity;
import com.example.BarangayConnect.Repository.EmergencyRepository;
import com.example.BarangayConnect.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<EmergencyEntity> emergency = emergencyRepository.findById(emergencyId);

        if (emergency.isPresent()) {
            EmergencyEntity entity = emergency.get();

            // Soft delete by updating isdelete field to 1
            entity.setIsdelete(1);
            emergencyRepository.save(entity);

            return "Alert " + emergencyId + " is marked as deleted!";
        } else {
            return "Alert " + emergencyId + " does not exist!";
        }
    }
}
