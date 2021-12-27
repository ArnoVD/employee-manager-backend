package com.arnovandijck.employeemanager.employee;

import com.arnovandijck.employeemanager.model.Employee;
import com.arnovandijck.employeemanager.repo.EmployeeRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepo underTest;

    @AfterEach
    void tearDown() {
        // After each test delete all the students
        underTest.deleteAll();
    }

    @Test
    public void itShouldCheckIfEmployeeExists() {
        // given
        String email = "arno@employee.com";
        Employee employee = new Employee ();
        employee.setFirstName("Arno");
        employee.setLastName("Vandijck");
        employee.setEmail("arno@employee.com");
        employee.setJobTitle("Developer");
        employee.setPhone("0479341128");
        employee.setImageUrl("http://bootdey.com/img/Content/avatar/avatar1.png");
        employee.setEmployeeId(UUID.randomUUID().toString());
        underTest.save(employee);

        // when
        Optional<Employee> expected = underTest.findEmployeeById(employee.getId());

        // then
        assertThat(expected).isNotNull();
    }

    @Test
    public void itShouldCheckIfEmployeeDoesNotExists() {
        // given
        String email = "arno@employee.com";
        Employee employee = new Employee ();
        employee.setFirstName("Arno");
        employee.setLastName("Vandijck");
        employee.setEmail("arno@employee.com");

        // when
        Optional<Employee> expected = underTest.findEmployeeById(employee.getId());

        // then
        assertThat(expected).isEmpty();
    }

}
