package com.securitytutorial.service;

import com.securitytutorial.model.EmployeeModel;
import com.securitytutorial.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo empRepo;

    public EmployeeModel addEmployee(EmployeeModel emp)
    {
        System.out.println(emp.getEmployeeName());
        return empRepo.save(emp);
    }

    public EmployeeModel getEmployee(int employeeId)
    {
        return empRepo.findById(employeeId).get();
    }

    public List<EmployeeModel> getAllEmployees()
    {
        return empRepo.findAll();
    }
}
