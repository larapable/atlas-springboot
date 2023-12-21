package com.example.BarangayConnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.AdminEntity;
import com.example.BarangayConnect.Service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<AdminEntity> login(@RequestBody AdminEntity admin) {
        AdminEntity adminEntity = adminService.loginUser(admin.getUsername(), admin.getPassword());
        if (adminEntity != null) {
            return ResponseEntity.ok(adminEntity);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
