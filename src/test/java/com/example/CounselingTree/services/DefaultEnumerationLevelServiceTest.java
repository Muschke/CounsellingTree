package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.repositories.EnumerationLevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultEnumerationLevelServiceTest {

    private DefaultEnumerationLevelService levelService;
    private EnumerationLevel level;

    @Mock
    EnumerationLevelRepository levelRepository;

    @BeforeEach
    void setUp() {
        levelService = new DefaultEnumerationLevelService(levelRepository);
        level = new EnumerationLevel("A1", "testDescription");
    }

    @Test
    void findAll() {
        levelService.findAll();
        verify(levelRepository).findAll();
    }

    @Test
    void findByDescription() {
        levelService.findByDescription("findTestDescription");
        verify(levelRepository).findByDescription("findTestDescription");
    }

    @Test
    void createEnumerationLevel() {
        levelService.createEnumerationLevel(level);
        verify(levelRepository).save(level);
    }
}