package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.StrengthEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.StrengthRepository;

@Service
public class StrengthService {
    @Autowired
    StrengthRepository strengthrepo;
   
     @Autowired
      DepartmentRepository departmentrepo;

       public StrengthEntity insertStrength(StrengthEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return strengthrepo.save(request);
    }

    public List<StrengthEntity> getStrengthsByDepartmentId(int departmentId) {
        return strengthrepo.findByDepartmentId(departmentId);
    }

    
    public StrengthEntity updateStrengthById(StrengthEntity request) {
        Optional<StrengthEntity> optionalStrength = strengthrepo.findById(request.getId());
        if (optionalStrength.isPresent()) {
            StrengthEntity existingStrength = optionalStrength.get();
            existingStrength.setValue(request.getValue());
            existingStrength.setDepartment(request.getDepartment());
            return strengthrepo.save(existingStrength);
        } else {
            throw new NoSuchElementException("Strength not found with ID: " + request.getId());
        }
    }

    public String deleteStrength(int strengthId) {
        String msg="";
        if (strengthrepo.findById(strengthId) != null) {
            strengthrepo.deleteById(strengthId);
            msg = "Strength "+strengthId+" is succesfully deleted!";
        } else 
            msg = "Strength "+strengthId+" does not exist!";
            return msg;
    } 


}