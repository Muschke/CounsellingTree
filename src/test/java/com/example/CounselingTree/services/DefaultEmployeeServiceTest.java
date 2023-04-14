package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.payload.EmployeeDto;
import com.example.CounselingTree.repositories.EmployeeRepository;
import com.example.CounselingTree.repositories.EnumerationLevelRepository;
import com.example.CounselingTree.repositories.UnitRepository;
import com.example.CounselingTree.services.interfaces.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultEmployeeServiceTest {
    private DefaultEmployeeService employeeService;
    private Employee employee;
    private Unit unit;
    private EnumerationLevel level;
    private EmployeeDto employeeDto;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private EnumerationLevelRepository levelRepository;

    @BeforeEach
    void beforeEach() {
        unit = new Unit("General Unit Test");
        level = new EnumerationLevel("D2", "Senior Manager Test");
        employeeService = new DefaultEmployeeService(employeeRepository, unitRepository, levelRepository);
        employee = new Employee("employeeName", "employeeNameSurname", LocalDate.of(2000,04,23), unit, level);
        employeeDto = new EmployeeDto("employeeName", "employeeNameSurname", LocalDate.of(2000,04,23),1L,1L);
    }

    @Test
    void findAllEmployeesWithRecordsWork() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findAllWithoutEmployeesRecordsTrowException(){
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()->employeeService.findAll());
    }


    @Test
    void createEmployee() {
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unit));
        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));

        employeeService.createEmployee(employeeDto);

        verify(employeeRepository).findByNameAndSurnameAndDateOfBirth(employeeDto.getName(), employeeDto.getSurname(), employeeDto.getDateOfBirth());
        verify(employeeRepository).save(employee);
    }
}