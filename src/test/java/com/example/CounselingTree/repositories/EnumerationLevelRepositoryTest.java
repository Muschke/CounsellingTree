package com.example.CounselingTree.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/insertLevel.sql")
class EnumerationLevelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String LEVELS = "enumerationlevels";
    private EnumerationLevelRepository levelRepository;

    public EnumerationLevelRepositoryTest(EnumerationLevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Test
    void findByDescriptionWorks() {
        assertThat(levelRepository.findByDescription("testleveldescription")).isPresent();
        assertThat(levelRepository.findByDescription("isnotpresent")).isNotPresent();
    }

    @Test
    void findByCodeWorks(){
        assertThat(levelRepository.findByCode("A1")).isPresent();
        assertThat(levelRepository.findByDescription("Z99")).isNotPresent();
    }

    @Test
    void findAllWorks(){
        assertThat(levelRepository.findAll()).hasSize(countRowsInTable(LEVELS));
    }

    @Test
    void findByIdWorks(){
        assertThat(levelRepository.findById(idTestLevel()))
                .hasValueSatisfying(level -> {
                    assertThat(level.getDescription()).isEqualTo("testleveldescription");
                    assertThat(level.getCode()).isEqualTo("A1");
                });
    }

    private long idTestLevel(){
        return jdbcTemplate.queryForObject("SELECT ID FROM enumerationlevels WHERE description ='testleveldescription'", Long.class);
    }
}