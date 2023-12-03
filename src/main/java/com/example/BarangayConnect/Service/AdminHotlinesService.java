package com.example.BarangayConnect.Service;

import com.example.BarangayConnect.Entity.AdminHotlinesEntity;
import com.example.BarangayConnect.Repository.AdminHotlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminHotlinesService {

    @Autowired
    private AdminHotlinesRepository hotlinesRepository;

    public AdminHotlinesEntity insertHotlineNumbers(AdminHotlinesEntity adminHotlinesEntity) {
        return hotlinesRepository.save(adminHotlinesEntity);
    }

    public List<AdminHotlinesEntity> getAllHotlineNumbers() {
        return hotlinesRepository.findAll();
    }

    public AdminHotlinesEntity updateAdminHotlineNumbers(int hotlineId,
            AdminHotlinesEntity newHotlineDetails) {
        AdminHotlinesEntity adminHotlines = hotlinesRepository.findById(hotlineId)
                .orElseThrow(() -> new NoSuchElementException("Hotline " + hotlineId + " not found"));

        adminHotlines.setTitle(newHotlineDetails.getTitle());
        adminHotlines.setHotlinenumber(newHotlineDetails.getHotlinenumber());
        adminHotlines.setIsdelete(newHotlineDetails.getIsdelete());

        return hotlinesRepository.save(adminHotlines);
    }

    public String deleteAdminHotlineNumbers(int hotlineId, boolean delete) {
        Optional<AdminHotlinesEntity> hotline = hotlinesRepository.findById(hotlineId);
    
        if (hotline.isPresent()) {
            AdminHotlinesEntity entity = hotline.get();
    
            // Set isdelete field based on the 'delete' parameter
            entity.setIsdelete(delete ? 1 : 0);
            hotlinesRepository.save(entity);
    
            return "Hotline " + hotlineId + " is marked as " + (delete ? "deleted" : "not deleted");
        } else {
            return "Hotline " + hotlineId + " does not exist!";
        }
    }
}