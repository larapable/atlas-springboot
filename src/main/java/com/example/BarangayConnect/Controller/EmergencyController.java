package com.example.BarangayConnect.Controller;

import com.example.BarangayConnect.Entity.EmergencyEntity;
import com.example.BarangayConnect.Service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/emergencies")
public class EmergencyController {

    @Autowired
    private EmergencyService emergencyService;

    @GetMapping("/all")
    public List<EmergencyEntity> getAllEmergencies() {
        return emergencyService.getAllEmergencies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyEntity> getEmergencyById(@PathVariable Integer id) {
        EmergencyEntity emergencyEntity = emergencyService.getEmergencyById(id);
        return ResponseEntity.of(java.util.Optional.ofNullable(emergencyEntity));
    }

    @PostMapping("/add")
    public EmergencyEntity addEmergency(@RequestBody EmergencyEntity emergencyEntity) {
        System.out.println("Received request with exactLocation: " + emergencyEntity.getExactLocation());
        return emergencyService.addEmergency(emergencyEntity);
    }

    @PutMapping("/update")
    public ResponseEntity<EmergencyEntity> updateEmergency(@RequestBody EmergencyEntity emergencyEntity) {
        EmergencyEntity updatedEmergency = emergencyService.updateEmergency(emergencyEntity);
        return ResponseEntity.ok(updatedEmergency);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmergency(@PathVariable Integer id) {
        emergencyService.deleteEmergency(id);
        return ResponseEntity.noContent().build();
    }
}
