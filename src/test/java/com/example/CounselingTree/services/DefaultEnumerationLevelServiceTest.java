package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.payload.Level;
import com.example.CounselingTree.repositories.EnumerationLevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultEnumerationLevelServiceTest {

    private DefaultEnumerationLevelService levelService;
    private EnumerationLevel level;
    private Level payloadlevel;

    @Mock
    EnumerationLevelRepository levelRepository;

    @BeforeEach
    void setUp() {
        levelService = new DefaultEnumerationLevelService(levelRepository);
        level = new EnumerationLevel("A1", "MockDescription");
        payloadlevel = new Level("A1", "MockDescription");
    }

    @Test
    void findAllWithRecordsWork() {
        when(levelRepository.findAll()).thenReturn(List.of(level));
        levelService.findAll();
        verify(levelRepository).findAll();
    }

    @Test
    void findAllWithoutRecordsTrowException(){
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()->levelService.findAll());

    }

    @Test
    void findByDescriptionWorks() {
        levelService.findByDescription("findTestDescription");
        verify(levelRepository).findByDescription("findTestDescription");
    }

    @Test
    void findByCodeWorks(){
        levelService.findByCode("A1");
        verify(levelRepository).findByCode("A1");
    }


    @Test
    void createEnumerationLevel() {
        levelService.createEnumerationLevel(payloadlevel);
        verify(levelRepository).save(level);
        verify(levelRepository).findByDescription("MockDescription");
        verify(levelRepository).findByCode("A1");
    }
}