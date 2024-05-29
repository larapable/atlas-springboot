package com.example.Atlas.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Repository.InternalRepository;
import com.example.Atlas.Repository.LearningRepository;
import com.example.Atlas.Repository.StakeholderRepository;

@Service
public class StratmapService {
    @Autowired
    FinancialRepository financialrepo;

    @Autowired
    StakeholderRepository stakeholderrepo;

    @Autowired
    LearningRepository learningrepo;

    @Autowired
    InternalRepository internalrepo;

     @Autowired
    DepartmentRepository departmentrepo;


    public Map<String, Object> getStrategiesByDepartmentId(int departmentId) {
        Map<String, Object> strategies = new HashMap<>();
        strategies.put("financial", financialrepo.findByDepartmentId(departmentId));
        strategies.put("stakeholder", stakeholderrepo.findByDepartmentId(departmentId));
        strategies.put("internalProcess", internalrepo.findByDepartmentId(departmentId));
        strategies.put("learningGrowth", learningrepo.findByDepartmentId(departmentId));
        return strategies;
    }

    public FinancialEntity insertFinancial(FinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return financialrepo.save(request);
    }
    
     public List<FinancialEntity> getFinancialByDepartmentId(int departmentId) {
        return financialrepo.findByDepartmentId(departmentId);
    }

    public FinancialEntity updateFinancialById(int id, String input, int departmentId) {
        Optional<FinancialEntity> optionalFinancial = financialrepo.findById(id);
        if (optionalFinancial.isPresent()) {
            FinancialEntity existingFinancial = optionalFinancial.get();
            Optional<DepartmentEntity> optionalDepartment = departmentrepo.findById(departmentId);
            if (optionalDepartment.isPresent()) {
                DepartmentEntity department = optionalDepartment.get();
                existingFinancial.setDepartment(department);
            } else {
                throw new NoSuchElementException("Department not found with ID: " + departmentId);
            }
            return financialrepo.save(existingFinancial);
        } else {
            throw new NoSuchElementException("Financial strategy not found with ID: " + id);
        }
    }

    public String deleteFinancial(int financialId) {
        String msg="";
        if (financialrepo.findById(financialId) != null) {
            financialrepo.deleteById(financialId);
            msg = "Strength "+financialId+" is succesfully deleted!";
        } else 
            msg = "Strength "+financialId+" does not exist!";
            return msg;
    } 
    
    


    
}
