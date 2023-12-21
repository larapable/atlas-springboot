package com.example.BarangayConnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarangayConnect.Entity.AdminEntity;
import com.example.BarangayConnect.Repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public AdminEntity loginUser(String username, String password) {
        AdminEntity user = adminRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
