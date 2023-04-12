package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.repositories.EmployeeRepository;
import com.example.CounselingTree.services.interfaces.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DefaultEmployeeServiceTest {
    private DefaultEmployeeService employeeService;
    private Employee employee;
    private Unit unit;
    private EnumerationLevel level;
    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void beforeEach() {
        unit = new Unit("General Unit Test");
        level = new EnumerationLevel("D2", "Senior Manager Test");
        employeeService = new DefaultEmployeeService(employeeRepository);
        employee = new Employee("employeeName", "employeeNameSurname", LocalDate.of(2000,04,23), unit, level);
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void createEmployee() {
        employeeService.createEmployee(employee);
        verify(employeeRepository).save(employee);
    }
}