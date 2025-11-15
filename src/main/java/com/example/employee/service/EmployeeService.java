package com.example.employee.service;

import com.example.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple in-memory service for employee management.
 */
@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public Employee createEmployee(Employee employee) {
        employee.setId(idGenerator.incrementAndGet());
        employees.add(employee);
        return employee;
    }

    public Optional<Employee> updateEmployee(Long id, Employee updated) {
        return getEmployeeById(id).map(emp -> {
            emp.setFirstName(updated.getFirstName());
            emp.setLastName(updated.getLastName());
            emp.setEmail(updated.getEmail());
            return emp;
        });
    }

    public boolean deleteEmployee(Long id) {
        return employees.removeIf(emp -> emp.getId().equals(id));
    }
}
