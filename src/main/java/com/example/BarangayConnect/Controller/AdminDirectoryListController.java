package com.example.BarangayConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @RequestBody AdminDirectoryListEntity adminDirectoryListEntity) {
        AdminDirectoryListEntity savedEntity = adminDirectoryListService
                .insertAdminDirectoryList(adminDirectoryListEntity);
        return ResponseEntity.ok(savedEntity);
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
