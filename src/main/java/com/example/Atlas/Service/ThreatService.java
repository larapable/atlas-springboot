package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.ThreatEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.ThreatRepository;

@Service
public class ThreatService {
    @Autowired
    ThreatRepository threatrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public ThreatEntity insertThreat(ThreatEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return threatrepo.save(request);
    }

    public List<ThreatEntity> getThreatsByDepartmentId(int departmentId) {
        return threatrepo.findByDepartmentId(departmentId);
    }

     public ThreatEntity updateThreatById(ThreatEntity request) {
        Optional<ThreatEntity> optionalThreat = threatrepo.findById(request.getId());
        if (optionalThreat.isPresent()) {
            ThreatEntity existingThreat= optionalThreat.get();
            existingThreat.setValue(request.getValue());
            existingThreat.setDepartment(request.getDepartment());
            return threatrepo.save(existingThreat);
        } else {
            throw new NoSuchElementException("Strength not found with ID: " + request.getId());
        }
    }

    public String deleteThreat(int threatId) {
        String msg="";
        if (threatrepo.findById(threatId) != null) {
            threatrepo.deleteById(threatId);
            msg = "Threat "+threatId+" is succesfully deleted!";
        } else 
            msg = "Threat "+threatId+" does not exist!";
            return msg;
    } 



    
}
