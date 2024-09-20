package com.sprk.rest_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprk.rest_app.entity.Employee;
import com.sprk.rest_app.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    // Dependency Injection
    // @Autowired // Field Injection
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Save Employee -> Insert

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {

        // Save Logic
        Employee savedEmployee = employeeService.insertEmployee(employee);

        return savedEmployee;
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<?> getEmployee(@PathVariable int empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
    }

    // FInd by Phone Number
    @GetMapping("/employee-with-phone/{phone}")
    public ResponseEntity<?> getEmployeeByPhone(@PathVariable String phone) {
        Employee employee = employeeService.getEmployeeByPhoneNum(phone);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
    }

    @DeleteMapping("/employee")
    public ResponseEntity<?> deleteEmployee(@RequestParam int empId) {

        Employee employee = employeeService.getEmployeeById(empId);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with empId = " + empId + " not found");
        } else {
            employeeService.deleteEmployee(employee);

            return ResponseEntity.status(HttpStatus.OK).body(employee);

        }

    }

    @PatchMapping("/employee/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable int empId, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(empId);
        if (existingEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        } else {
            employee.setEmpId(empId);
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        }
    }

}