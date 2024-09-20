package com.sprk.rest_app.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprk.rest_app.entity.Employee;
import com.sprk.rest_app.repository.EmployeeRepository;
import com.sprk.rest_app.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Dependency Injection
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int empId) {

        Optional<Employee> dbEmployee = employeeRepository.findById(empId);

        if (dbEmployee.isPresent()) {
            return dbEmployee.get();
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByPhoneNum(String phone) {
        Optional<Employee> dbEmployee = employeeRepository.findByPhone(phone);

        if (dbEmployee.isPresent()) {
            return dbEmployee.get();
        }
        return null;
    }

}