package com.example.CounselingTree.services.interfaces;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.EmployeeDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    void createEmployee(EmployeeDto employeeDto);

    Optional<Employee> findByNameAndSurnameAndDateOfBirthWorks(String name, String surname, LocalDate dateOfBirth);
}
