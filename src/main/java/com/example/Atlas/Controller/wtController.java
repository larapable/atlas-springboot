package com.example.Atlas.Controller;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Atlas.Entity.wtEntity;
import com.example.Atlas.Service.wtService;

@RestController
@RequestMapping("/wtStrat")
@CrossOrigin
public class wtController {
    @Autowired
    wtService wtserv;

     @PostMapping("insert")
    public wtEntity insertWtStrat(@RequestBody wtEntity wtStrat) {
        return wtserv.insertWtStrat(wtStrat);
    }
    

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getWtStratByDepartmentId(@PathVariable int departmentId) {
        try {
            List<wtEntity> wtStrat = wtserv.getWtStratByDepartmentId(departmentId);
            if (wtStrat != null && !wtStrat.isEmpty()) {
                return ResponseEntity.ok(wtStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No W-TStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWt(@PathVariable int id) {
        try {
            wtserv.deleteWt(id);
            return ResponseEntity.ok("W-T with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete W-T: " + e.getMessage());
        }
    }


    
}
