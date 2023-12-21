package com.example.BarangayConnect.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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

    // Add Business
    @PostMapping("/insertAdminBusiness")
    public ResponseEntity<BusinessEntity> insertAdminBusiness(@RequestBody BusinessEntity busEntity) {
        BusinessEntity savedEntity = busService.insertAdminBusiness(busEntity);
        return ResponseEntity.ok(savedEntity);
    }

    // Add Business with Image
    @PostMapping("/insertAdminBusinessWithImage")
    public ResponseEntity<BusinessEntity> insertAdminBusinessImage(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("date") Date date,
            @RequestParam("image") MultipartFile image) throws IOException {
        // Upload the image and get the path
        String imagePath = busService.uploadImage(image);

        // Create a new BusinessEntity
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setBusTitle(title);
        businessEntity.setBusContent(description);
        businessEntity.setDate(date);
        businessEntity.setPhotoPath(imagePath); // Set the photo path to the returned image path

        // Save the new BusinessEntity
        BusinessEntity savedEntity = busService.insertAdminBusiness(businessEntity);

        return ResponseEntity.ok(savedEntity);
    }

    // Add Image to Business using businessId
    @PostMapping("/uploadImage/{businessId}")
    public BusinessEntity uploadImagebyId(
            @PathVariable int businessId,
            @RequestParam("image") MultipartFile image) throws IOException {
        busService.uploadImage(image);
        BusinessEntity businessEntity = busService.findById(businessId);
        businessEntity.setPhotoPath(image.getOriginalFilename());
        return busService.addBusinessImage(businessEntity);
    }

    // Get all Business
    @GetMapping("/getAllBusiness")
    public List<BusinessEntity> getAllBusiness() {
        return busService.getAllBusiness();
    }

    // Update Business by Id
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
