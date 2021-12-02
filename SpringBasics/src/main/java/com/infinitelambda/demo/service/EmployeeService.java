package com.infinitelambda.demo.service;

import com.infinitelambda.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void createEmployee(String name);

    void removeEmployee(Long id);

    void updateEmployee(Long id, String name);

     List<Employee> findAllEmployee();
}
