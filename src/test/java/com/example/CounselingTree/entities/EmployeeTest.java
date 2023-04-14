package com.example.CounselingTree.entities;

import com.example.CounselingTree.enums.Status;
import com.example.CounselingTree.exception.InvalidCounselorException;
import com.example.CounselingTree.exception.InvalidLevelCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Unit unit;
    private EnumerationLevel levelSenior;
    private  EnumerationLevel SeniorConsultant;
    private EnumerationLevel levelJuniorA;
    private EnumerationLevel levelJuniorB;

    private Employee counsellor;
    private Employee counsellorTwo;
    private Employee counsellorLevelSeniorConsultant;
    private Employee counselleeOne;
    private Employee counselleeTwo;
    private Employee counselleeThree;
    private LocalDate dateLessOneYearAgo;
    private LocalDate dateMoreThanOneYearAgo;

    @BeforeEach
    void beforeEach() {
        dateLessOneYearAgo = LocalDate.now().minusMonths(8);
        dateMoreThanOneYearAgo = LocalDate.now().minusYears(1).minusMonths(2);
        unit = new Unit("General Unit Test");
        levelSenior = new EnumerationLevel("D2", "Senior Manager Test");
        SeniorConsultant= new EnumerationLevel("C1", "Senior Consultant");
        levelJuniorA = new EnumerationLevel("A3", "Young Professional Test");
        levelJuniorB = new EnumerationLevel("A4", "Consultant Test");

        counsellor = new Employee("counsellorName", "counsellorSurname", LocalDate.of(1990,02,28), LocalDate.of(2015, 03, 15),
                unit, levelSenior);
        counsellorTwo = new Employee("counsellorNameTwo", "counsellorSurnameTwo", LocalDate.of(1992,1,15), LocalDate.of(2013, 03, 15),
                unit, levelSenior);

        counsellorLevelSeniorConsultant= new Employee("counsellorNameThree", "counsellorSurnameThree", LocalDate.of(1994,4,23), LocalDate.of(2019, 8, 24),
                unit, SeniorConsultant);

        counselleeOne = new Employee("counselleeOneName", "counselleeOneSurname", LocalDate.of(1995,8,15), dateLessOneYearAgo,
                unit, levelJuniorA);
        counselleeTwo = new Employee("counselleeTwoName", "counselleeTwoSurname", LocalDate.of(2000,04,23), dateMoreThanOneYearAgo,
                unit, levelJuniorB);
        counselleeThree = new Employee("counselleeThreeName", "counselleeThreeSurname", LocalDate.of(2000,04,23), dateLessOneYearAgo,
                unit, levelJuniorA);

    }

    @Test
    void addCounselleeToValidWorks() {
        assertThat(counsellor.getCounsellees().isEmpty());

        counsellor.addCounsellee(counselleeOne);
        assertThat(counsellor.getCounsellees()).containsOnly(counselleeOne);
        assertThat(counsellor.getCounsellees()).doesNotContain(counselleeTwo);
        assertThat(counselleeOne.getCounsellor()).isEqualTo(counsellor);


        counsellor.addCounsellee(counselleeTwo);
        assertThat(counsellor.getCounsellees()).containsAll(Arrays.asList(counselleeOne, counselleeTwo));
        assertThat(counselleeTwo.getCounsellor()).isEqualTo(counsellor);
    }


    @Test
    void addingCounsellorWithInvalidSeniorityThrowsException(){
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()->counselleeTwo.changeOrSetCounsellor(counselleeOne));
    }

    @Test
    void addingCounsellorWithLevelLowerThanCToCounselleeThrowsException(){
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()->counselleeOne.changeOrSetCounsellor(counselleeTwo));
    }

    @Test
    void addingCounsellorWithLevelLowerOrEqualToCounselleeToCounselleeThrowsException(){
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()->counsellorTwo.changeOrSetCounsellor(counsellor));
    }


    @Test
    void addCounselleeToCounselorWithInvalidSeniorityThrowsException(){
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()->counselleeOne.addCounsellee(counselleeTwo));
    }

    @Test
    void addCounselleeToCounselorWithLevelLowerThanCThrowsException(){
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()->counselleeTwo.addCounsellee(counselleeOne));
    }

    @Test
    void addCounselleeToCounselorWithLevelLowerOrEqualToCounselleeThrowsException(){
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()->counsellor.addCounsellee(counsellorTwo));
    }

    @Test
    void addingMoreThanTwoCounselleesToCounselorWithLevelCThrowsException(){
        counsellorLevelSeniorConsultant.addCounsellee(counselleeOne);
        counsellorLevelSeniorConsultant.addCounsellee(counselleeTwo);
        assertThatExceptionOfType(InvalidCounselorException.class).isThrownBy(()-> counsellorLevelSeniorConsultant.addCounsellee(counselleeThree));


    }

    @Test
    void removeCounselleeWork() {
        counsellor.addCounsellee(counselleeOne);
        counsellor.addCounsellee(counselleeTwo);
        assertThat(counsellor.getCounsellees()).containsAll(Arrays.asList(counselleeOne, counselleeTwo));

        counsellor.removeCounsellee(counselleeOne);
        assertThat(counsellor.getCounsellees()).containsOnly(counselleeTwo);
        assertThat(counsellor.getCounsellees()).doesNotContain(counselleeOne);
        assertThat(counselleeOne.getCounsellor()).isEqualTo(null);

        counsellor.removeCounsellee(counselleeTwo);
        assertThat(counsellor.getCounsellees()).isEmpty();
        assertThat(counselleeTwo.getCounsellor()).isEqualTo(null);
    }

    @Test
    void changeOrSetCounsellorWorks(){
        assertThat(counselleeOne.getCounsellor()).isNull();
        assertThat(counsellor.getCounsellees()).isEmpty();

        counselleeOne.changeOrSetCounsellor(counsellor);
        assertThat(counselleeOne.getCounsellor()).isEqualTo(counsellor);
        assertThat(counsellor.getCounsellees()).containsOnly(counselleeOne);

        counselleeOne.changeOrSetCounsellor(counsellorTwo);
        assertThat(counselleeOne.getCounsellor()).isEqualTo(counsellorTwo);
        assertThat(counsellorTwo.getCounsellees()).containsOnly(counselleeOne);
        assertThat(counsellor.getCounsellees()).isEmpty();
    }

    @Test
    void endContractWorks(){
        counsellor.addCounsellee(counselleeOne);
        counsellor.addCounsellee(counselleeTwo);
        assertThat(counselleeOne.getCounsellor()).isEqualTo(counsellor);
        assertThat(counselleeTwo.getCounsellor()).isEqualTo(counsellor);

        counsellor.endContract();

        assertThat(counselleeOne.getCounsellor()).isNull();
        assertThat(counselleeTwo.getCounsellor()).isNull();
        assertThat(counsellor.getCounsellees()).isEmpty();
        assertThat(counsellor.getStatus()).isEqualTo(Status.INACTIVE);
        assertThat(counsellor.getEndDateContract()).isEqualTo(LocalDate.now());
    }
}