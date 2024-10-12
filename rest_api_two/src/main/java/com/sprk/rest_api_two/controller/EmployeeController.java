package com.sprk.rest_api_two.controller;

import java.util.List;

import com.sprk.rest_api_two.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprk.rest_api_two.dto.EmployeeDto;
import com.sprk.rest_api_two.dto.ResponseDto;
import com.sprk.rest_api_two.service.EmployeeService;

@Tag(
        name = "Employee CRUD Api",
        description = "Create, Read, Update, Delete mappings of Employee"
)
@RestController
@RequestMapping("/api")

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            description = "Employee Post API to save Employee Object",
            summary = "Save Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Saved successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Email or phone number already exists",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
                    )
            }
    )

    @PostMapping("/employee")
    public ResponseEntity<ResponseDto<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }


    @Operation(
            description = "Employee Get API to Get All Employees",
            summary = "Get Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get all employees successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
                    )
            }
    )
    @GetMapping("/employee")
    public ResponseEntity<ResponseDto<List<EmployeeDto>>> getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ResponseDto("200", employeeDtoList));
    }



    @Operation(
            description = "Employee Get API to Get Employee by EmpId",
            summary = "Get Mapping by EmpId"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get employee successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
                    )
            }
    )
    @GetMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> getEmployeeByEmpId(@PathVariable String empId) {

        EmployeeDto employeeDto = employeeService.getByEmpId(empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto));
    }

    @DeleteMapping("/employee/{empId}")
    @Operation(
            description = "Employee Delete API to delete Employee Object",
            summary = "Delete Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted Successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee with emp id not found",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )

                    )
            }
    )
            public ResponseEntity<ResponseDto<String>> deleteEmployeeByEmpId(@PathVariable String empId) {

        employeeService.deleteEmployee(empId);

        return ResponseEntity.status(200).body(new ResponseDto("200", ("Employee with emp id: " + empId + " deleted successfully")));
    }



    @Operation(
            description = "Employee PUT API to Update Employee Object",
            summary = "Update Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated Successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee with emp id Updated successfully",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )

                    )
            }
    )
    @PutMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable String empId) {

        EmployeeDto employeeDto1= employeeService.updateEmployeeByEmpId(employeeDto,empId);


        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }



    @PutMapping("/employee/V1/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployeeV1(@RequestBody EmployeeDto employeeDto,@PathVariable String empId) {

        EmployeeDto employeeDto1 = employeeService.updateEmployeeByIdV1(employeeDto, empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }
    

}