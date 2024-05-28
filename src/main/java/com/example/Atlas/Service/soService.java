package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.soRepository;
import com.example.Atlas.Entity.soEntity;

@Service
public class soService {
    @Autowired
    soRepository sorepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public soEntity insertSoStrat(soEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return sorepo.save(request);
    }
    
     public List<soEntity> getSoStratByDepartmentId(int departmentId) {
        return sorepo.findByDepartmentId(departmentId);
    }

    public String deleteSo(int soId) {
        String msg="";
        if (sorepo.findById(soId) != null) {
            sorepo.deleteById(soId);
            msg = "S-O "+soId+" is succesfully deleted!";
        } else 
            msg = "S-O "+soId+" does not exist!";
            return msg;
    } 
    
}
