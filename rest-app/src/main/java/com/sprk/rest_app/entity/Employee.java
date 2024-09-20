package com.sprk.rest_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increament
    private int empId;

    private String firstName;

    private String lastName;

    @Column(length = 15, nullable = false, unique = true)
    private String phone;

    private double salary;
}
