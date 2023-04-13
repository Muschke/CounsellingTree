package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.Level;
import com.example.CounselingTree.services.interfaces.EnumerationLevelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EnumerationLevelControllerTest {
    private EnumerationLevelController levelController;

    @Mock
    private EnumerationLevelService levelService;

    private EnumerationLevel mocklevel;
    private Level mockpayload;
    @BeforeEach
    void beforeEach() {
        levelController = new EnumerationLevelController(levelService);
        mocklevel = new EnumerationLevel("A1", "MockDescription");
        mockpayload = new Level("A1", "MockDescription");
    }

    @Test
    void getAllUnits() {
        levelController.getAllUnits();
        verify(levelService).findAll();
    }

    @Test
    void addNewLevel() {
        levelController.addNewLevel(mockpayload);
        verify(levelService).createEnumerationLevel(mockpayload);
    }
}