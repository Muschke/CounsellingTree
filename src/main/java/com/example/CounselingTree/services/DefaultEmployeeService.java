package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.repositories.EmployeeRepository;
import com.example.CounselingTree.services.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultEmployeeService implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public DefaultEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
