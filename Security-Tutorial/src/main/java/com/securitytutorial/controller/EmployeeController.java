package com.securitytutorial.controller;

import com.securitytutorial.model.EmployeeModel;
import com.securitytutorial.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeService empService;

    @PostMapping("addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeModel employee)
    {
        return new ResponseEntity<>(empService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("getEmployee/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable int employeeId)
    {
        return new ResponseEntity<>(empService.getEmployee(employeeId),HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAllEmployee()
    {
        return new ResponseEntity<>(empService.getAllEmployees(),HttpStatus.OK);
    }
}
