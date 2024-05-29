package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.StrengthEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Service.StratmapService;

@RestController
@RequestMapping("/stratmap")
@CrossOrigin
public class StratmapController {
    @Autowired
    StratmapService stratmapserv;

    @Autowired
    DepartmentRepository departmentrepo;

    @Autowired  
    FinancialRepository financialrepo;

    @GetMapping("/{departmentId}")
    public ResponseEntity<Map<String, Object>> getStrategiesByDepartmentId(@PathVariable int departmentId) {
        try {
            Map<String, Object> strategies = stratmapserv.getStrategiesByDepartmentId(departmentId);
            return ResponseEntity.ok(strategies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Failed to fetch strategies"));
        }
    }

    @PostMapping("/financial/insert")
    public FinancialEntity insertFinancial(@RequestBody FinancialEntity financial) {
        return stratmapserv.insertFinancial(financial);
    }
    

    @GetMapping("/financial/get/{departmentId}")
    public ResponseEntity<?> getFinancialByDepartmentId(@PathVariable int departmentId) {
        try {
            List<FinancialEntity> financial = stratmapserv.getFinancialByDepartmentId(departmentId);
            if (financial != null && !financial.isEmpty()) {
                return ResponseEntity.ok(financial);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No financial found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

      @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFinancial(@PathVariable int id) {
        try {
            stratmapserv.deleteFinancial(id);
            return ResponseEntity.ok("Financial with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete financial: " + e.getMessage());
        }
    }

    
}
