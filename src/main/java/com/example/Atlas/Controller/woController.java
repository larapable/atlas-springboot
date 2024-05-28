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

import com.example.Atlas.Entity.woEntity;
import com.example.Atlas.Service.woService;

@RestController
@RequestMapping("/woStrat")
@CrossOrigin
public class woController {
    @Autowired
    woService woserv;

     @PostMapping("insert")
    public woEntity insertWoStrat(@RequestBody woEntity woStrat) {
        return woserv.insertWoStrat(woStrat);
    }
    

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getWoStratByDepartmentId(@PathVariable int departmentId) {
        try {
            List<woEntity> woStrat = woserv.getWoStratByDepartmentId(departmentId);
            if (woStrat != null && !woStrat.isEmpty()) {
                return ResponseEntity.ok(woStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No W-OStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWo(@PathVariable int id) {
        try {
            woserv.deleteWo(id);
            return ResponseEntity.ok("W-O with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete W-O: " + e.getMessage());
        }
    }
    
}
