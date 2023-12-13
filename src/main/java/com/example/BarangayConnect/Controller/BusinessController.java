package com.example.BarangayConnect.Controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.BarangayConnect.Entity.BusinessEntity;
import com.example.BarangayConnect.Service.BusinessService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/business")
public class BusinessController {
    
    @Autowired
    private BusinessService busService;

    @PostMapping("/insertAdminBusiness")
    public ResponseEntity<BusinessEntity> insertAdminBusiness(  @RequestBody BusinessEntity busEntity) {
        BusinessEntity savedEntity = busService.insertAdminBusiness(busEntity);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getAllBusiness")
    public List<BusinessEntity> getAllBusiness() {
        return busService.getAllBusiness();
    }

    @PutMapping("/updateBusiness/{busId}")
    public BusinessEntity updateBusiness(@PathVariable int busId,
            @RequestBody BusinessEntity busEntity) {
        return busService.updateBusiness(busId, busEntity);
    }

    @DeleteMapping("/deleteBusiness/{busId}")
    public void deleteBusiness(@PathVariable int busId) {
        busService.deleteBusiness(busId);
    }

    @GetMapping("getInfoById/{busId}")
    public BusinessEntity getBusinessID(@PathVariable int busId) {
        return busService.findById(busId);
    }




    // Add Image to BusinessEntity using businessId
    @PostMapping("/uploadImage/{businessId}")
    public ResponseEntity<String> uploadImage(
            @PathVariable int businessId,
            @RequestParam("image") MultipartFile image) {
        try {
            busService.uploadImage(image);
            BusinessEntity businessEntity = busService.findById(businessId);
            businessEntity.setPhotoPath(image.getOriginalFilename());
            busService.updateBusiness(businessId, businessEntity);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // Get image from BusinessEntity using businessId
    @GetMapping("/getBusinessImage/{businessId}")
    public ResponseEntity<String> getBusinessImage(@PathVariable int businessId) {
        try {
            BusinessEntity businessEntity = busService.findById(businessId);

            if (businessEntity != null && businessEntity.getPhotoPath() != null) {
                return ResponseEntity.ok(businessEntity.getPhotoPath());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
