package com.springdemo.department_service.controller;

import com.netflix.discovery.converters.Auto;
import com.springdemo.department_service.client.EmployeeClient;
import com.springdemo.department_service.model.Department;
import com.springdemo.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department ->
                department.setEmployees(employeeClient.findByDepartmentId(department.getId())));

        return departments;
    }

}
