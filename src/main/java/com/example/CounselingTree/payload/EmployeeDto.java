package com.example.CounselingTree.payload;

import java.time.LocalDate;

public class EmployeeDto {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    private Long levelId;
    private Long unitId;

    public EmployeeDto(String name, String surname, LocalDate dateOfBirth, Long levelId, Long unitId){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.levelId = levelId;
        this.unitId = unitId;
    }

    protected EmployeeDto(){};

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getLevelId() {
        return levelId;
    }

    public Long getUnitId() {
        return unitId;
    }
}

