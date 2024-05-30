package com.example.Atlas.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentImageEntity;
import com.example.Atlas.Repository.DepartmentImageRepository;
import com.example.Atlas.Repository.DepartmentRepository;

@Service
public class DepartmentImageService {
    @Autowired
    DepartmentImageRepository departmentImageRepo;
    @Autowired
    DepartmentRepository departmentRepo;

    public boolean imageExists(int departmentId) {
        return departmentImageRepo.findByDepartmentId(departmentId).isPresent();
    }

    public boolean insertDepartmentImage(int departmentId, byte[] image, String imageFormat) {
        try {
            DepartmentImageEntity departmentImage = new DepartmentImageEntity();
            departmentImage.setDepartment(departmentRepo.findById(departmentId).orElseThrow());
            departmentImage.setImage(image);
            departmentImage.setImageFormat(imageFormat);
            departmentImageRepo.save(departmentImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDepartmentImage(int departmentId, byte[] imageData, String imageFormat) {
        try {
            DepartmentImageEntity departmentImage = departmentImageRepo.findByDepartmentId(departmentId)
                    .orElseThrow();
            departmentImage.setImage(imageData);
            departmentImage.setImageFormat(imageFormat);
            departmentImageRepo.save(departmentImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<DepartmentImageEntity> getDepartmentImage(int departmentId) {
        return departmentImageRepo.findByDepartmentId(departmentId);
    }

}
