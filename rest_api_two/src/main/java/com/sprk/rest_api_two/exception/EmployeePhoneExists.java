package com.sprk.rest_api_two.exception;

public class EmployeePhoneExists extends ResourseNotFound {

    public EmployeePhoneExists(String message, int statusCode) {
        super(message, statusCode);
    }
}
