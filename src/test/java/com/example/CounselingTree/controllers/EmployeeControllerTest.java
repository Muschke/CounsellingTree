package com.example.CounselingTree.controllers;

import com.example.CounselingTree.payload.EmployeeDto;
import com.example.CounselingTree.services.interfaces.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    private EmployeeController employeeController;
    private EmployeeDto employeeDto;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void beforeEach() {
        employeeController = new EmployeeController(employeeService);
        employeeDto = new EmployeeDto("employeeName", "employeeNameSurname", LocalDate.of(2000,04,23),1L,1L);
    }

    @Test
    void getAllUnits() {
        employeeController.getAllUnits();
        verify(employeeService).findAll();
    }

    @Test
    void addNewEmployee() {
        employeeController.addNewEmployee(employeeDto);
        verify(employeeService).createEmployee(employeeDto);
    }
}