package com.arnovandijck.employeemanager.service;

import com.arnovandijck.employeemanager.exception.UserNotFoundException;
import com.arnovandijck.employeemanager.model.Employee;
import com.arnovandijck.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employeeToUpdate) {
        return employeeRepo.save(employeeToUpdate);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found."));
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
