package com.example.BarangayConnect.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BarangayConnect.Entity.AdminDirectoryListEntity;
import com.example.BarangayConnect.Repository.AdminDirectoryListRepository;
import org.springframework.util.StringUtils; // Import StringUtils from Spring

@Service
public class AdminDirectoryListService {
    @Autowired
    private AdminDirectoryListRepository adminDirectoryListRepository;

    public AdminDirectoryListEntity insertAdminDirectoryList(AdminDirectoryListEntity adminDirectoryListEntity,
            MultipartFile image) throws IOException {
        String imageName = saveImage(image);
        adminDirectoryListEntity.setImageName(imageName);
        return adminDirectoryListRepository.save(adminDirectoryListEntity);
    }

    public String saveImage(MultipartFile image) throws IOException {
        String originalFilename = StringUtils.cleanPath(image.getOriginalFilename());
    
        // Use the original filename without modification
        String imageName = originalFilename;
    
        // Implement the logic to save the image with the imageName
        // Example: Save the image to a folder with the imageName
        // ... (save the image to a folder)
    
        return imageName;
    }

    public AdminDirectoryListEntity getAdminDirectoryListById(int admindirectorylistId) {
        return adminDirectoryListRepository.findById(admindirectorylistId)
                .orElseThrow(() -> new NoSuchElementException("Directory " + admindirectorylistId + " not found"));
    }

    public List<AdminDirectoryListEntity> getAllAdminDirectoryList() {
        return adminDirectoryListRepository.findAll();
    }

    public AdminDirectoryListEntity updateAdminDirectoryList(int admindirectorylistId,
            AdminDirectoryListEntity newDirectoryDetails) {
        AdminDirectoryListEntity adminDirectoryList = adminDirectoryListRepository.findById(admindirectorylistId)
                .orElseThrow(() -> new NoSuchElementException("Directory " + admindirectorylistId + " not found"));

        adminDirectoryList.setName(newDirectoryDetails.getName());
        adminDirectoryList.setAge(newDirectoryDetails.getAge());
        adminDirectoryList.setEmail(newDirectoryDetails.getEmail());
        adminDirectoryList.setStatus(newDirectoryDetails.getStatus());
        adminDirectoryList.setBirthdate(newDirectoryDetails.getBirthdate());
        adminDirectoryList.setPosition(newDirectoryDetails.getPosition());
        adminDirectoryList.setMessage(newDirectoryDetails.getMessage());

        return adminDirectoryListRepository.save(adminDirectoryList);
    }

    public String deleteAdminDirectoryList(int admindirectorylistId) {
        Optional<AdminDirectoryListEntity> directory = adminDirectoryListRepository.findById(admindirectorylistId);

        if (directory.isPresent()) {
            AdminDirectoryListEntity entity = directory.get();

            // Soft delete by updating isdelete field to 1
            entity.setIsdelete(1);
            adminDirectoryListRepository.save(entity);

            return "Directory " + admindirectorylistId + " is marked as deleted!";
        } else {
            return "Directory " + admindirectorylistId + " does not exist!";
        }
    }
}
