package com.example.CounselingTree.entities;

import com.example.CounselingTree.exception.InvalidLevelCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EnumerationLevelTest {
    private String validDescription;
    private String validCode;

    @BeforeEach
    void setUp() {
        validDescription = "testDescription";
        validCode = "A1";
    }

    @Test
    void validcodeworks(){
        EnumerationLevel level = new EnumerationLevel(validCode, validDescription);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  "})
    void invalidDescriptionGetsRejected(String invalidDescription){
        assertThatIllegalArgumentException().isThrownBy(()->new EnumerationLevel(validCode, invalidDescription));
    }

    @ParameterizedTest
    @ValueSource(strings = { "G1", "", "A", "1", "a1", "aaa1", "a111", "aaa111"})
    void invalidLevelCodesGetsRejected(String invalidcode){
        assertThatExceptionOfType(InvalidLevelCodeException.class).isThrownBy(()->new EnumerationLevel(invalidcode, validDescription));
    }
}