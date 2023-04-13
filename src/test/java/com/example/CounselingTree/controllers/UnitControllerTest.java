package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.services.interfaces.UnitService;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UnitControllerTest {
    private UnitController unitController;
    @Mock
    private UnitService unitService;

    @BeforeEach
    void beforeEach() {
        unitController = new UnitController(unitService);
    }

    @Test
    void getAllUnits() {
        unitController.getAllUnits();
        verify(unitService).findAll();
    }

    @Test
    void addNewUnit() {
        unitController.addNewUnit("new Unitname");
        verify(unitService).createUnit("new Unitname");
    }
}