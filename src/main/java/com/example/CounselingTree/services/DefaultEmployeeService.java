package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.exception.ExistsAlreadyInDatabaseException;
import com.example.CounselingTree.payload.EmployeeDto;
import com.example.CounselingTree.repositories.EmployeeRepository;
import com.example.CounselingTree.repositories.EnumerationLevelRepository;
import com.example.CounselingTree.repositories.UnitRepository;
import com.example.CounselingTree.services.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultEmployeeService implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private UnitRepository unitRepository;
    private EnumerationLevelRepository levelRepository;

    public DefaultEmployeeService(EmployeeRepository employeeRepository, UnitRepository unitRepository, EnumerationLevelRepository levelRepository) {
        this.employeeRepository = employeeRepository;
        this.unitRepository = unitRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> response = employeeRepository.findAll();
        if(!response.isEmpty()){
            return response;
        } else{
            throw new NoSuchElementException("No employees present");
        }
    }

    @Override
    public Optional<Employee> findByNameAndSurnameAndDateOfBirthWorks(String name, String surname, LocalDate dateOfBirth){
        return employeeRepository.findByNameAndSurnameAndDateOfBirth(name, surname, dateOfBirth);
    }

    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        if(this.findByNameAndSurnameAndDateOfBirthWorks(employeeDto.getName(), employeeDto.getSurname(), employeeDto.getDateOfBirth()).isPresent()){
            throw new ExistsAlreadyInDatabaseException("employee with that name, surname and date of birth already exists");
        } else {
            employeeRepository.save(new Employee(employeeDto.getName(), employeeDto.getSurname(), employeeDto.getDateOfBirth(),
                    unitRepository.findById(employeeDto.getUnitId()).get(), levelRepository.findById(employeeDto.getLevelId()).get()));
        }

    }
}
