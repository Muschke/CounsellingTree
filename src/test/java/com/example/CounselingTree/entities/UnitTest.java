package com.example.CounselingTree.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void validUnitworks(){
        Unit unit = new Unit("testUnit");
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  "})
    void invalidUnitGetsRejected(String invalidUnit){
        assertThatIllegalArgumentException().isThrownBy(()->new Unit(invalidUnit));
    }

}