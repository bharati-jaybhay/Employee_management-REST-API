package com.sprk.rest_api_two.service;


import java.util.List;

import com.sprk.rest_api_two.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getByEmpId(String empId);

    List<EmployeeDto>getAllEmployees();

    void deleteEmployee(String empId);

    EmployeeDto updateEmployeeByIdV1(EmployeeDto employeeDto, String empId);

    EmployeeDto updateEmployeeByEmpId(EmployeeDto employeeDto, String empId);
}
