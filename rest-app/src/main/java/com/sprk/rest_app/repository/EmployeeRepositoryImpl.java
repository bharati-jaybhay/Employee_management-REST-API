// package com.sprk.rest_app.repository;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;

// import com.sprk.rest_app.entity.Employee;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.TypedQuery;

// @Repository

// public class EmployeeRepositoryImpl implements EmployeeRepository {
//     // Hibernate

//     // Dependency Injection

//     private final EntityManager entityManager;

//     @Autowired
//     public EmployeeRepositoryImpl(EntityManager entityManager) {
//         this.entityManager = entityManager;
//     }

//     @Override
//     public Employee saveEmployee(Employee employee) {
//         return entityManager.merge(employee);
//     }

//     @Override
//     public List<Employee> getAllEmployees() {

//         TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

//         return query.getResultList();
//     }

//     @Override
//     public Employee FindById(int empId) {
//         Employee employee = entityManager.find(Employee.class, empId);
//         return employee;
//     }

//     @Override
//     public void removeEmployee(Employee employee) {
//         entityManager.remove(employee);
//     }

//     @Override
//     public Employee updateEmployee(Employee employee) {

//         return entityManager.merge(employee);

//     }

// }
