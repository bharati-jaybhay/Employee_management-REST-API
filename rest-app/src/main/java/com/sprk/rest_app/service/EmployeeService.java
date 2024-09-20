package com.sprk.rest_app.service;

import java.util.List;

import com.sprk.rest_app.entity.Employee;

public interface EmployeeService {

    Employee insertEmployee(Employee employee);

    List<Employee> findAll();

    Employee getEmployeeById(int empId);

    void deleteEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee getEmployeeByPhoneNum(String phone);

}
