package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Repository.OpportunityRepository;
import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.OpportunityEntity;
import com.example.Atlas.Repository.DepartmentRepository;


@Service
public class OpportunityService {
    @Autowired
    OpportunityRepository opportunityrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public OpportunityEntity insertOpportunity(OpportunityEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return opportunityrepo.save(request);
    }

    public List<OpportunityEntity> getOpportunityByDepartmentId(int departmentId) {
        return opportunityrepo.findByDepartmentId(departmentId);
    }

     public OpportunityEntity updateOpportunityById(OpportunityEntity request) {
        Optional<OpportunityEntity> optionalOpportunity = opportunityrepo.findById(request.getId());
        if (optionalOpportunity.isPresent()) {
            OpportunityEntity existingOpportunity = optionalOpportunity.get();
            existingOpportunity.setValue(request.getValue());
            existingOpportunity.setDepartment(request.getDepartment());
            return opportunityrepo.save(existingOpportunity);
        } else {
            throw new NoSuchElementException("Opportunity not found with ID: " + request.getId());
        }
    }

    public String deleteOpportunity(int opportunityId) {
        String msg="";
        if (opportunityrepo.findById(opportunityId) != null) {
            opportunityrepo.deleteById(opportunityId);
            msg = "Opportunity "+opportunityId+" is succesfully deleted!";
        } else 
            msg = "Opportunity "+opportunityId+" does not exist!";
            return msg;
    } 
    
}
