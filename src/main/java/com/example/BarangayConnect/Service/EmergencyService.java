package com.example.BarangayConnect.Service;

import com.example.BarangayConnect.Entity.EmergencyEntity;
import com.example.BarangayConnect.Repository.EmergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmergencyService {

    @Autowired
    private EmergencyRepository emergencyRepository;

    public List<EmergencyEntity> getAllEmergencies() {
        return emergencyRepository.findAll();
    }

    public EmergencyEntity getEmergencyById(Integer id) {
        Optional<EmergencyEntity> optionalEmergency = emergencyRepository.findById(id);
        return optionalEmergency.orElse(null);
    }

    public EmergencyEntity addEmergency(EmergencyEntity emergencyEntity) {
        // No need to handle user persistence since there is no user reference in EmergencyEntity
        return emergencyRepository.save(emergencyEntity);
    }

    public EmergencyEntity updateEmergency(EmergencyEntity emergencyEntity) {
        return emergencyRepository.save(emergencyEntity);
    }

    public void deleteEmergency(Integer id) {
        emergencyRepository.deleteById(id);
    }
}
