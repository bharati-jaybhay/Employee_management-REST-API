package com.sprk.rest_api_two.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String empId;

    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 15)
    private String phone;

    private String department;

    private String designation;

    private LocalDate dateOfJoining;

}

