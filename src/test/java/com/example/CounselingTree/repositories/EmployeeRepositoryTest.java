package com.example.CounselingTree.repositories;

import com.example.CounselingTree.enums.Status;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql({"/insertLevel.sql", "/insertUnit.sql","/insertEmployee.sql"})
class EmployeeRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String EMPLOYEES = "employees";
    private EmployeeRepository employeeRepository;

    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void  findByNameAndSurnameAndDateOfBirthWorks(){
        assertThat(employeeRepository.findByNameAndSurnameAndDateOfBirth("testname", "testsurname",
                LocalDate.of(1990,01,01)))
                .hasValueSatisfying(employee -> {
                    assertThat(employee.getStatus()).isEqualTo(Status.ACTIVE);
                    assertThat(employee.getStartDateContract()).isEqualTo(LocalDate.of(2015,2,1));
                });
    }

    @Test
    void findAllWorks(){
        assertThat(employeeRepository.findAll()).hasSize(countRowsInTable(EMPLOYEES));
    }

    @Test
    void findByIdWorks(){
        assertThat(employeeRepository.findById(idTestEmployee()))
                .hasValueSatisfying(employee -> {
                    assertThat(employee.getSurname()).isEqualTo("testsurname");
                    assertThat(employee.getStartDateContract()).isEqualTo(LocalDate.of(2015,2,1));
                });
    }

    private long idTestEmployee(){
        return jdbcTemplate.queryForObject("SELECT ID FROM employees WHERE name ='testname'", Long.class);
    }
}