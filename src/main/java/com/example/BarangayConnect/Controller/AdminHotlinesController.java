package com.example.BarangayConnect.Controller;

import com.example.BarangayConnect.Entity.AdminHotlinesEntity;
import com.example.BarangayConnect.Service.AdminHotlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotlines")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminHotlinesController {

    @Autowired
    private AdminHotlinesService hotlinesService;

    @PostMapping("/insertHotlineNumbers")
    public ResponseEntity<AdminHotlinesEntity> insertHotlineNumber(
            @RequestBody AdminHotlinesEntity adminHotlinesEntity) {
        AdminHotlinesEntity savedEntity = hotlinesService.insertHotlineNumbers(adminHotlinesEntity);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getAllHotlineNumbers")
    public List<AdminHotlinesEntity> getAllHotlineNumbers() {
        return hotlinesService.getAllHotlineNumbers();
    }

    @PutMapping("/updateAdminHotlineNumbers/{hotlineId}")
    public AdminHotlinesEntity updateAdminHotlineNumbers(@PathVariable int hotlineId,
            @RequestBody AdminHotlinesEntity adminHotlinesEntity) {
        return hotlinesService.updateAdminHotlineNumbers(hotlineId, adminHotlinesEntity);
    }

    @DeleteMapping("/deleteAdminHotlineNumbers/{hotlineId}")
    public ResponseEntity<String> deleteAdminHotlineNumbers(@PathVariable int hotlineId,
            @RequestParam(name = "delete", defaultValue = "false") boolean delete) {
        String result = hotlinesService.deleteAdminHotlineNumbers(hotlineId, delete);
        return ResponseEntity.ok(result);
    }
}