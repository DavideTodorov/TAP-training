package com.infinitelambda.demo;

import com.infinitelambda.demo.entity.Employee;
import com.infinitelambda.demo.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final EmployeeService employeeService;

    public DemoApplication(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private void createEmployee(String name) {
        employeeService.createEmployee(name);
    }

    private void removeEmployee(Long id) {
        employeeService.removeEmployee(id);
    }

    private void updateEmployee(Long id, String name) {
        employeeService.updateEmployee(id, name);
    }

    private void findAllEmployee() {
        for (Employee employee : employeeService.findAllEmployee()) {
            System.out.println(employee.getName());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        createEmployee("Employee 1");
        createEmployee("Employee 2");
        findAllEmployee();
        updateEmployee(1L, "New Employee 1");
        findAllEmployee();
        removeEmployee(1L);
        findAllEmployee();
    }
}
