package com.example.Atlas.Service;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.UserEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userrepo;

    @Autowired
    DepartmentRepository departmentrepo;

   
    
    public UserEntity insertUser(UserEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        request.setDepartment(department);
        return userrepo.save(request);
    }

    public boolean checkUserExists(String email, String username) {
        return userrepo.existsByEmail(email);
    }

    public boolean checkUserNameExists(String username) {
        return userrepo.existsByUsername(username);
    }
 
}
