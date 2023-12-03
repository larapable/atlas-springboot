package com.example.BarangayConnect.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarangayConnect.Entity.AdminDirectoryListEntity;
import com.example.BarangayConnect.Repository.AdminDirectoryListRepository;

@Service
public class AdminDirectoryListService {
    @Autowired
    private AdminDirectoryListRepository adminDirectoryListRepository;

    public AdminDirectoryListEntity insertAdminDirectoryList(AdminDirectoryListEntity adminDirectoryListEntity) {
        return adminDirectoryListRepository.save(adminDirectoryListEntity);
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
