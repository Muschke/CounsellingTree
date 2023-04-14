package com.example.CounselingTree.services.interfaces;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.CounsellorAndCounsellee;
import com.example.CounselingTree.payload.EmployeeDto;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    void createEmployee(EmployeeDto employeeDto);
    Optional<Employee> findByNameAndSurnameAndDateOfBirthWorks(String name, String surname, LocalDate dateOfBirth);
    void setCounsellorForEmployee(CounsellorAndCounsellee counsellorAndCounsellee);
    void addCounseleeToCounselor(CounsellorAndCounsellee counsellorAndCounsellee);
    void removeCounseleeFromCounselor(CounsellorAndCounsellee counsellorAndCounsellee);
    Optional<Employee> findById(long id);

    void terminateContract(long id);
}
