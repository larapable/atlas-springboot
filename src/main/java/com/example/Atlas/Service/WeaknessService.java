package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.WeaknessRepository;
import com.example.Atlas.Entity.WeaknessEntity;



@Service
public class WeaknessService {
    @Autowired
    WeaknessRepository weaknessrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public WeaknessEntity insertWeakness(WeaknessEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return weaknessrepo.save(request);
    }
    
     public List<WeaknessEntity> getWeaknessByDepartmentId(int departmentId) {
        return weaknessrepo.findByDepartmentId(departmentId);
    }

    public WeaknessEntity updateWeaknessById(WeaknessEntity request) {
        Optional<WeaknessEntity> optionalWeakness = weaknessrepo.findById(request.getId());
        if (optionalWeakness.isPresent()) {
            WeaknessEntity existingWeakness = optionalWeakness.get();
            existingWeakness.setValue(request.getValue());
            existingWeakness.setDepartment(request.getDepartment());
            return weaknessrepo.save(existingWeakness);
        } else {
            throw new NoSuchElementException("Weakness not found with ID: " + request.getId());
        }
    }

    public String deleteWeakness(int weaknessId) {
        String msg="";
        if (weaknessrepo.findById(weaknessId) != null) {
            weaknessrepo.deleteById(weaknessId);
            msg = "Weakness "+weaknessId+" is succesfully deleted!";
        } else 
            msg = "Weakness "+weaknessId+" does not exist!";
            return msg;
    } 

    
}
