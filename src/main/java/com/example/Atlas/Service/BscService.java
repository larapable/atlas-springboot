package com.example.Atlas.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.InternalEntity;
import com.example.Atlas.Entity.StakeholderEntity;
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Repository.LearningRepository;
import com.example.Atlas.Repository.StakeholderRepository;
import com.example.Atlas.Repository.InternalRepository;


@Service
public class BscService {
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

    public FinancialEntity insertFinancialBsc(FinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return financialrepo.save(request);
    }

    public List<FinancialEntity> findFinancialByDepartmentId(int departmentId) {
        return financialrepo.findByDepartmentId(departmentId);
    }

    public FinancialEntity updateFinancialBscById(int id, FinancialEntity request) {
        Optional<FinancialEntity> optionalFinancial = financialrepo.findById(request.getId());
        if (optionalFinancial.isPresent()) {
            FinancialEntity existingFinancial = optionalFinancial.get();
            existingFinancial.setTarget_code(request.getTarget_code());
            existingFinancial.setOffice_target(request.getOffice_target());
            existingFinancial.setStartDate(request.getStartDate());
            existingFinancial.setCompletionDate(request.getCompletionDate());
            existingFinancial.setStatus(request.getStatus());
            existingFinancial.setKey_performance_indicator(request.getKey_performance_indicator());
            existingFinancial.setTarget_performance(request.getTarget_performance());
            existingFinancial.setActual_performance(request.getActual_performance());
            // Assuming Department is an entity, handle it accordingly
            // existingFinancial.setDepartment(request.getDepartment());
            return financialrepo.save(existingFinancial);
        } else {
            throw new NoSuchElementException("Financial scorecard not found with ID: " + request.getId());
        }
    }


    //Stakeholder

    public StakeholderEntity insertStakeholderBsc(StakeholderEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return stakeholderrepo.save(request);
    }

    public List<StakeholderEntity> findStakeholderByDepartmentId(int departmentId) {
        return stakeholderrepo.findByDepartmentId(departmentId);
    }

    public StakeholderEntity updateStakeholderBscById(int id, StakeholderEntity request) {
        Optional<StakeholderEntity> optionalStakeholder = stakeholderrepo.findById(request.getId());
        if (optionalStakeholder.isPresent()) {
            StakeholderEntity existingStakeholder = optionalStakeholder.get();
            existingStakeholder.setTarget_code(request.getTarget_code());
            existingStakeholder.setOffice_target(request.getOffice_target());
            existingStakeholder.setStartDate(request.getStartDate());
            existingStakeholder.setCompletionDate(request.getCompletionDate());
            existingStakeholder.setStatus(request.getStatus());
            existingStakeholder.setKey_performance_indicator(request.getKey_performance_indicator());
            existingStakeholder.setTarget_performance(request.getTarget_performance());
            existingStakeholder.setActual_performance(request.getActual_performance());
            // Assuming Department is an entity, handle it accordingly
            // existingFinancial.setDepartment(request.getDepartment());
            return stakeholderrepo.save(existingStakeholder);
        } else {
            throw new NoSuchElementException("Stakeholder scorecard not found with ID: " + request.getId());
        }
    }

    //Learning
    public LearningEntity insertLearningBsc(LearningEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return learningrepo.save(request);
    }

    public List<LearningEntity> findLearningByDepartmentId(int departmentId) {
        return learningrepo.findByDepartmentId(departmentId);
    }

    //Internal
    public InternalEntity insertInternalBsc(InternalEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return internalrepo.save(request);
    }

    public List<InternalEntity> findInternalByDepartmentId(int departmentId) {
        return internalrepo.findByDepartmentId(departmentId);
    }

   

}
