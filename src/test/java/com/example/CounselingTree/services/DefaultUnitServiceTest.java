package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.repositories.UnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultUnitServiceTest {

    private DefaultUnitService unitService;
    private Unit unit;
    @Mock
    private UnitRepository unitRepository;

    @BeforeEach
    void beforeEach() {
        unitService = new DefaultUnitService(unitRepository);
        unit = new Unit("testUnit");
    }

    @Test
    void findByName() {
        unitService.findByName("findTestUnit");
        verify(unitRepository).findByName("findTestUnit");
    }

    @Test
    void createUnit() {
        unitService.createUnit("newUnit");
        verify(unitRepository).findByName("newUnit");
        verify(unitRepository).save(new Unit("newUnit"));
    }

    @Test
    void findAll() {
        unitService.findAll();
        verify(unitRepository).findAll();
    }
}