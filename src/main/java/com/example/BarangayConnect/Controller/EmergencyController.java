package com.example.BarangayConnect.Controller;

import com.example.BarangayConnect.Entity.EmergencyEntity;
import com.example.BarangayConnect.Service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
@CrossOrigin(origins = "http://localhost:3000")
public class EmergencyController {

    @Autowired
    EmergencyService emergencyService;

    @PostMapping("/insertEmergency")
    public EmergencyEntity insertEmergency(@RequestBody EmergencyEntity emergencyEntity) {
        System.out.println(emergencyEntity.getUser().getId());
        return emergencyService.insertEmergency(emergencyEntity);
    }
    
    @GetMapping("/getAllEmergency")
    public List<EmergencyEntity> getAllEmergency() {
        return emergencyService.getAllEmergency();
    }

    @PutMapping("/updateEmergency")
    public EmergencyEntity updateEmergency(@RequestParam int emergencyId, @RequestBody EmergencyEntity emergencyEntity) {
        return emergencyService.updateEmergency(emergencyId, emergencyEntity);
    }

    @DeleteMapping("/deleteEmergency/{emergencyId}")
    public void deleteEmergency(@PathVariable int emergencyId) {
        emergencyService.deleteEmergency(emergencyId);
    }
}
