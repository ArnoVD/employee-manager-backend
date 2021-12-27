package com.arnovandijck.employeemanager.repo;

import com.arnovandijck.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    // Because of the naming convention spring automatically knows what this method should do.
    void deleteEmployeeById(Long id);
    // Optional is used for the situation where there is no employee with the given id.
    Optional<Employee> findEmployeeById(Long id);
}
