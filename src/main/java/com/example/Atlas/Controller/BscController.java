package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.InternalEntity;
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Entity.StakeholderEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Service.BscService;

@RestController
@RequestMapping("/bsc")
@CrossOrigin("http://localhost:3000")
public class BscController {
    @Autowired
    BscService bscserv;

    @Autowired
    DepartmentRepository departmentrepo;

    @PostMapping("/financialBsc/insert")
    public FinancialEntity insertFinancialBsc(@RequestBody FinancialEntity financial) {
        return bscserv.insertFinancialBsc(financial);
    }

    @GetMapping("financial/get/{departmentId}")
    public ResponseEntity<?> getFinancialByDepartmentId(@PathVariable int departmentId) {
        try {
            List<FinancialEntity> financial = bscserv.findFinancialByDepartmentId(departmentId);
            if (financial != null && !financial.isEmpty()) {
                return ResponseEntity.ok(financial);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No financial found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    
    @PutMapping("/financial/update/{id}")
    public ResponseEntity<FinancialEntity> updateFinancialBsc(@PathVariable int id, @RequestBody FinancialEntity request) {
        try {
            request.setId(id); // Ensure the ID from the path variable is set in the request entity
            FinancialEntity updatedFinancial = bscserv.updateFinancialBscById(id, request);
            return ResponseEntity.ok(updatedFinancial);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

    //Stakeholder
    @PostMapping("/stakeholderBsc/insert")
    public StakeholderEntity insertStakeholderBsc(@RequestBody StakeholderEntity stakeholder) {
        return bscserv.insertStakeholderBsc(stakeholder);
    }

    @GetMapping("stakeholder/get/{departmentId}")
    public ResponseEntity<?> getStakeholderByDepartmentId(@PathVariable int departmentId) {
        try {
            List<StakeholderEntity> stakeholder = bscserv.findStakeholderByDepartmentId(departmentId);
            if (stakeholder != null && !stakeholder.isEmpty()) {
                return ResponseEntity.ok(stakeholder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No stakeholder found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/stakeholder/update/{id}")
    public ResponseEntity<StakeholderEntity> updateStakeholderBscById(@PathVariable int id, @RequestBody StakeholderEntity request) {
        try {
            request.setId(id); // Ensure the ID from the path variable is set in the request entity
            StakeholderEntity updatedStakeholder = bscserv.updateStakeholderBscById(id, request);
            return ResponseEntity.ok(updatedStakeholder);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Learning
    @PostMapping("/learningBsc/insert")
    public LearningEntity insertlearningBsc(@RequestBody LearningEntity learning) {
        return bscserv.insertLearningBsc(learning);
    }

    
    @GetMapping("learning/get/{departmentId}")
    public ResponseEntity<?> getLearningByDepartmentId(@PathVariable int departmentId) {
        try {
            List<LearningEntity> learning = bscserv.findLearningByDepartmentId(departmentId);
            if (learning != null && !learning.isEmpty()) {
                return ResponseEntity.ok(learning);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No learning found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    //Intenal
    @PostMapping("/internalBsc/insert")
    public InternalEntity insertInternalBsc(@RequestBody InternalEntity internal) {
        return bscserv.insertInternalBsc(internal);
    }

    
    @GetMapping("internal/get/{departmentId}")
    public ResponseEntity<?> getInternalByDepartmentId(@PathVariable int departmentId) {
        try {
            List<InternalEntity> internal = bscserv.findInternalByDepartmentId(departmentId);
            if (internal != null && !internal.isEmpty()) {
                return ResponseEntity.ok(internal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No internal found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }




}
