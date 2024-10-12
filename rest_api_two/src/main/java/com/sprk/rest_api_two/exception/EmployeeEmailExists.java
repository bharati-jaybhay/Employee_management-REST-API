package com.sprk.rest_api_two.exception;

public class EmployeeEmailExists extends ResourseNotFound{


    public EmployeeEmailExists(String message , int statusCode) {
        super(message, statusCode);
    }
}
