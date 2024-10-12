package com.sprk.rest_api_two.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sprk.rest_api_two.dto.ErrorResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourseNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ResourseNotFound resourseNotFound, HttpServletRequest request) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getServletPath(), resourseNotFound.getStatusCode(),resourseNotFound.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(resourseNotFound.getStatusCode()).body(errorResponseDto);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(EmployeeEmailExists.class)
//    public ResponseEntity<ErrorResponseDto> handleEmployeeWithEmailAlreadyExists(EmployeeEmailExists employeeWithEmailAlreadyExists,
//            HttpServletRequest request) {
//
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getContextPath(), "500",
//                employeeWithEmailAlreadyExists.getMessage(), LocalDateTime.now());
//
//        return ResponseEntity.status(500).body(errorResponseDto);
//    }

//    @ExceptionHandler(EmployeePhoneExists.class)
//    public ResponseEntity<ErrorResponseDto> handleEmployeeWithPhoneAlreadyExists(EmployeePhoneExists employeeWithPhoneAlreadyExists, HttpServletRequest request) {
//
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getContextPath(), "500",employeeWithPhoneAlreadyExists.getMessage(), LocalDateTime.now());
//
//        return ResponseEntity.status(500).body(errorResponseDto);
//    }
}