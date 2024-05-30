package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;


@Service
public class BscService {
    @Autowired
    FinancialRepository financialrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public FinancialEntity insertFinancialBsc(FinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return financialrepo.save(request);
    }

    public List<FinancialEntity> findByDepartmentId(int departmentId) {
        return financialrepo.findByDepartmentId(departmentId);
    }

}
