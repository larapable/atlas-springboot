package com.example.BarangayConnect.Controller;

import com.example.BarangayConnect.Entity.AdminSubmitEmergencyEntity;
import com.example.BarangayConnect.Service.AdminSubmitEmergencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminemergency")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminSubmitEmergencyController {

    @Autowired
    AdminSubmitEmergencyService adminSubmitEmergencyService;

    @PostMapping("/admininsertEmergency")
    public AdminSubmitEmergencyEntity insertEmergency(@RequestBody AdminSubmitEmergencyEntity adminSubmitEmergencyEntity) {
        return adminSubmitEmergencyService.insertEmergency(adminSubmitEmergencyEntity);
    }
    
    @GetMapping("/admingetAllEmergency")
    public List<AdminSubmitEmergencyEntity> getAllEmergency() {
        return adminSubmitEmergencyService.getAllEmergency();
    }

    @PutMapping("/adminupdateEmergency/{adminemergencyId}")
    public AdminSubmitEmergencyEntity updateEmergency(@PathVariable int adminemergencyId, 
            @RequestBody AdminSubmitEmergencyEntity adminSubmitEmergencyEntity) {
        return adminSubmitEmergencyService.updateEmergency(adminemergencyId, adminSubmitEmergencyEntity);
    }

    @DeleteMapping("/admindeleteEmergency/{adminemergencyId}")
    public ResponseEntity<String> deleteEmergency(@PathVariable int adminemergencyId) {
        String result = adminSubmitEmergencyService.deleteEmergency(adminemergencyId);
        return ResponseEntity.ok(result);   
    }
}
