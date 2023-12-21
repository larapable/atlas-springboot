package com.example.BarangayConnect.Service;

import com.example.BarangayConnect.Entity.AdminSubmitEmergencyEntity;
import com.example.BarangayConnect.Repository.AdminSubmitEmergencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminSubmitEmergencyService {

    @Autowired
    AdminSubmitEmergencyRepository adminSubmitEmergencyRepository;

    public AdminSubmitEmergencyEntity insertEmergency(AdminSubmitEmergencyEntity emergencyEntity) {
        return adminSubmitEmergencyRepository.save(emergencyEntity);
    }

    public List<AdminSubmitEmergencyEntity> getAllEmergency() {
        return adminSubmitEmergencyRepository.findAll();
    }

    public AdminSubmitEmergencyEntity updateEmergency(int adminemergencyId,
            AdminSubmitEmergencyEntity newEmergencyDetails) {
        AdminSubmitEmergencyEntity emergency = adminSubmitEmergencyRepository.findById(adminemergencyId)
                .orElseThrow(() -> new NoSuchElementException("Emergency " + adminemergencyId + " not found"));

        emergency.setTypeOfIncident(newEmergencyDetails.getTypeOfIncident());
        emergency.setDate(newEmergencyDetails.getDate());
        emergency.setTime(newEmergencyDetails.getTime());
        emergency.setExactLocation(newEmergencyDetails.getExactLocation());
        emergency.setIncidentDetails(newEmergencyDetails.getIncidentDetails());

        return adminSubmitEmergencyRepository.save(emergency);
    }

    public AdminSubmitEmergencyEntity getAlertAadmin(int adminemergencyId) {
        return adminSubmitEmergencyRepository.findById(adminemergencyId).get();
    }

    public String deleteEmergency(int adminemergencyId) {
        Optional<AdminSubmitEmergencyEntity> emergency = adminSubmitEmergencyRepository.findById(adminemergencyId);

        if (emergency.isPresent()) {
            AdminSubmitEmergencyEntity entity = emergency.get();
            // Soft delete by updating isdelete field to 1
            entity.setIsdelete(1);
            adminSubmitEmergencyRepository.save(entity);

            return "Alert " + adminemergencyId + " is marked as deleted!";
        } else {
            return "Alert " + adminemergencyId + " does not exist!";
        }
    }
}
