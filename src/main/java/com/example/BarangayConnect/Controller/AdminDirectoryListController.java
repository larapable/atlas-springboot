package com.example.BarangayConnect.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.BarangayConnect.Entity.AdminDirectoryListEntity;
import com.example.BarangayConnect.Service.AdminDirectoryListService;

@RestController
@RequestMapping("/admindirectorylist")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDirectoryListController {

    @Autowired
    private AdminDirectoryListService adminDirectoryListService;

    @PostMapping("/insertAdminDirectoryList")
    public ResponseEntity<AdminDirectoryListEntity> insertAdminDirectoryList(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("age") int age,
            @RequestParam("status") String status,
            @RequestParam("birthdate") Date birthdate,
            @RequestParam("position") String position,
            @RequestParam("message") String message
    ) {
        try {
            // Save the image and get the image name
            String imageName = adminDirectoryListService.saveImage(image);

            // Create an AdminDirectoryListEntity object
            AdminDirectoryListEntity adminDirectoryListEntity = new AdminDirectoryListEntity();
            adminDirectoryListEntity.setName(name);
            adminDirectoryListEntity.setEmail(email);
            adminDirectoryListEntity.setAge(age);
            adminDirectoryListEntity.setStatus(status);
            adminDirectoryListEntity.setBirthdate(birthdate);
            adminDirectoryListEntity.setPosition(position);
            adminDirectoryListEntity.setMessage(message);
            adminDirectoryListEntity.setImageName(imageName);

            // Save the entity
            AdminDirectoryListEntity savedEntity = adminDirectoryListService.insertAdminDirectoryList(adminDirectoryListEntity, image);

            return ResponseEntity.ok(savedEntity);
        } catch (Exception e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllAdminDirectoryList")
    public List<AdminDirectoryListEntity> getAllAdminDirectoryList() {
        return adminDirectoryListService.getAllAdminDirectoryList();
    }

    @PutMapping("/updateAdminDirectoryList/{admindirectorylistId}")
    public AdminDirectoryListEntity updateAdminDirectoryList(@PathVariable int admindirectorylistId,
            @RequestBody AdminDirectoryListEntity adminDirectoryListEntity) {
        return adminDirectoryListService.updateAdminDirectoryList(admindirectorylistId, adminDirectoryListEntity);
    }

    @DeleteMapping("/deleteAdminDirectoryList/{admindirectorylistId}")
    public void deleteAdminDirectoryList(@PathVariable int admindirectorylistId) {
        adminDirectoryListService.deleteAdminDirectoryList(admindirectorylistId);
    }
}
