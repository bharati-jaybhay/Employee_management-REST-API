package com.sprk.rest_api_two.service.Impl;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprk.rest_api_two.dto.EmployeeDto;
import com.sprk.rest_api_two.entity.Employee;
import com.sprk.rest_api_two.exception.EmployeeEmailExists;
import com.sprk.rest_api_two.exception.EmployeePhoneExists;
import com.sprk.rest_api_two.exception.EmployeewithEmpIdExist;
import com.sprk.rest_api_two.mapper.EmployeeMApper;
import static com.sprk.rest_api_two.mapper.EmployeeMApper.ConvertEmployeeDtoToEmployee;
import com.sprk.rest_api_two.repository.EmployeeRepository;
import com.sprk.rest_api_two.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        employeeDto.setEmpId(null);

        // Check if the email or phone already exists in the system
        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent( employee -> {
                    throw new EmployeeEmailExists("Employee with email " +employee.getEmail() + " already exists", 400);
                });

        employeeRepository.findByPhone(employeeDto.getPhone())
                .ifPresent(employee -> {
                    throw new EmployeePhoneExists("Employee with phone " + employee.getPhone() + " already exists", 400);
                });

                 // Convert employeeDto to Employee;
        Employee employee = new Employee();

        employee = ConvertEmployeeDtoToEmployee(employeeDto, employee);

        // Logic to generate empID
        employee.setEmpId(generateEmpId(employee.getFirstName()));
        employee.setDateOfJoining(LocalDate.now());

//        Employee savedEmployee=
        employeeRepository.save(employee);

        employeeDto.setEmpId(employee.getEmpId());
        return employeeDto;

    }

// OR

    // @Override
    // public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

    //     employeeDto.setEmpId(null);

    //     Optional<Employee> employeeWithEmail = employeeRepository.findByEmail(employeeDto.getEmail());
    //     Optional<Employee> employeeWithPhone = employeeRepository.findByPhone(employeeDto.getPhone());

    //     if (employeeWithEmail.isPresent()) {
    //         throw new EmployeeEmailExists("Employee with " + employeeDto.getEmail() + " already exists", 400);
    //     }
    //     if (employeeWithPhone.isPresent()) {
    //         throw new EmployeePhoneExists("Employee with " + employeeDto.getPhone() + " already exists", 400);
    //     }

    

    @Override
    public EmployeeDto getByEmpId(String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeewithEmpIdExist("Employee with " + empId + " not found!",404));

        return EmployeeMApper.ConvertEmployeeToDto(employee, new EmployeeDto());
    }


    @Override
    public List<EmployeeDto> getAllEmployees() {
        //        GPT Code
        return employeeRepository.findAll()
                .stream()
                .map(employee -> EmployeeMApper.ConvertEmployeeToDto(employee, new EmployeeDto()))
                .toList();
    }

    @Override
    public void deleteEmployee(String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeewithEmpIdExist("Employee with " + empId + " not found!",404));
        employeeRepository.delete(employee);

    }


    private String generateEmpId(String firstName) {
        // Get the first two characters of the first name
        String company = "SPRK";
        String fNameChar = firstName.substring(0, 2).toUpperCase();

        // Get the current year
        String year = String.valueOf(Year.now().getValue()).substring(2);

        // Generate a random UUID (universally unique identifier)
        String uniqueId = UUID.randomUUID().toString().substring(0, 8); // Get first 8 chars of UUID

        // Combine prefix, year, and UUID to form the unique empId
        return (company + year + fNameChar + uniqueId).toUpperCase();
    }


    @Override
    public EmployeeDto updateEmployeeByIdV1(EmployeeDto employeeDto, String empId) {
    
        Employee employee= employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeewithEmpIdExist("Employee with " + empId + " not found!",404));

         // Set new values to employee object
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setDesignation(employeeDto.getDesignation());

        employeeRepository.save(employee);
        employeeDto.setEmpId(empId);

        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployeeByEmpId(EmployeeDto employeeDto, String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeewithEmpIdExist("Employee with " + empId + " not found!",404));

        employeeRepository.updateEmployee(employeeDto.getFirstName(),employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getPhone(),employeeDto.getDepartment(), employeeDto.getDesignation(), empId);

        employeeDto.setEmpId(empId);
        return employeeDto;
    }

}