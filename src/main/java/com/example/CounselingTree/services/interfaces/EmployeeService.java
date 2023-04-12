package com.example.CounselingTree.services.interfaces;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    void createEmployee(Employee employee);
}
