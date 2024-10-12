package com.sprk.rest_api_two.mapper;

import com.sprk.rest_api_two.dto.EmployeeDto;
import com.sprk.rest_api_two.entity.Employee;

public class EmployeeMApper {

    public static Employee ConvertEmployeeDtoToEmployee(EmployeeDto employeeDto, Employee employee) {

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setDesignation(employeeDto.getDesignation());
        return employee;
    }

    public static EmployeeDto ConvertEmployeeToDto(Employee employee, EmployeeDto employeeDto) {

        employeeDto.setEmpId(employee.getEmpId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setDesignation(employee.getDesignation());
        return employeeDto;
    }
}