package com.springdemo.employee_service.controller;


import com.springdemo.employee_service.model.Employee;
import com.springdemo.employee_service.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return employeeRepository.add(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable Long departmentId) {
        return employeeRepository.findByDepartment(departmentId);
    }
}
