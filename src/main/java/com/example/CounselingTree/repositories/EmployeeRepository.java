package com.example.CounselingTree.repositories;

import com.example.CounselingTree.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByNameAndSurnameAndDateOfBirth(String name, String surname, LocalDate dateOfBirth);
}
