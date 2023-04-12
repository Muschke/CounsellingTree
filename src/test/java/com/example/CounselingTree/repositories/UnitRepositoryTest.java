package com.example.CounselingTree.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/insertUnit.sql")
class UnitRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String UNITS = "units";
    private UnitRepository unitRepository;

    public UnitRepositoryTest(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Test
    void findByNameWorks() {
        assertThat(unitRepository.findByName("testunit")).isPresent();
    }

    @Test
    void findByIdWorks(){
        assertThat(unitRepository.findById(idTestUnit()))
                .hasValueSatisfying(unit -> assertThat(unit.getName()).isEqualTo("testunit"));
    }

    @Test
    void findAllWorks(){
        assertThat(unitRepository.findAll()).hasSize(countRowsInTable(UNITS));
    }

    private long idTestUnit(){
        return jdbcTemplate.queryForObject("SELECT id FROM units WHERE name = 'testunit'", Long.class);
    }
}