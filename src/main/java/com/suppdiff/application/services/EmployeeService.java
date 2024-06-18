package com.suppdiff.application.services;

import java.util.List;
import com.suppdiff.domain.entities.Employee;
import com.suppdiff.repositories.EmployeeRepositoryImpl;

public class EmployeeService {
    private EmployeeRepositoryImpl employeeRepositoryImpl = new EmployeeRepositoryImpl();
    
    public void save(Employee employee) {
        employeeRepositoryImpl.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepositoryImpl.getAll();
    }

    public Employee getById(int id) {
        return employeeRepositoryImpl.getById(id);
    }

    public void delete(int id) {
        employeeRepositoryImpl.delete(id);
    }
}
