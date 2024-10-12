package com.sprk.rest_api_two.exception;

public class EmployeewithEmpIdExist extends ResourseNotFound{

 public EmployeewithEmpIdExist(String message, int statusCode) {
        super(message, statusCode);
    }
}
