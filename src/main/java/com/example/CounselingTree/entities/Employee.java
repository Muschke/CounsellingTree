package com.example.CounselingTree.entities;

import com.example.CounselingTree.enums.Status;
import com.example.CounselingTree.exception.InvalidCounselorException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private Status status;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private LocalDate startDateContract;
    private LocalDate endDateContract;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enumerationlevels_id", referencedColumnName = "id")
    private EnumerationLevel level;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Employee counsellor;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Employee> counsellees;

    public Employee(String name, String surname, LocalDate dateOfBirth, Unit unit, EnumerationLevel level) {
        this.status = Status.ACTIVE;
        setName(name);
        setSurname(surname);
        this.dateOfBirth = dateOfBirth;
        this.startDateContract = LocalDate.now();
        this.unit = unit;
        this.level = level;
        this.counsellees = new HashSet<>();
    }
    public Employee(String name, String surname, LocalDate dateOfBirth, LocalDate startDateContract, Unit unit, EnumerationLevel level) {
        this.status = Status.ACTIVE;
        setName(name);
        setSurname(surname);
        this.dateOfBirth = dateOfBirth;
        this.startDateContract = startDateContract;
        this.unit = unit;
        this.level = level;
        this.counsellees = new HashSet<>();
    }

    protected Employee(){}

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public void setLevel(EnumerationLevel level){
        this.level = level;
    }
    public void endContract(){
        this.setEndDateContract();
        this.setStatus(Status.INACTIVE);
        this.setCounsellor(null);
        if(!this.getCounsellees().isEmpty()){
            for(Employee employee: this.getCounsellees()){
                employee.setCounsellor(null);
            }
           this.removeAllCounsellees();
        }
    }
    public void changeOrSetCounsellor(Employee counsellor){
        if(counsellor == null){
            throw new NullPointerException();
        }
        if(this.getCounsellor() != null){
            this.getCounsellor().removeCounsellee(this);
        }
        counsellor.addCounsellee(this);
        this.setCounsellor(counsellor);
    }
    public boolean addCounsellee(Employee counsellee){
        if(counsellee == null){
            throw new NullPointerException();
        }
        if(!this.employeeHasOneYearSeniority()){
            throw new InvalidCounselorException("Employee doesn't have the required seniority");
        }
        counsellee.setCounsellor(this);
        return counsellees.add(counsellee);
    }
    public boolean removeCounsellee(Employee counsellee){
        if(counsellee == null){
            throw new NullPointerException();
        }
        if(!counsellees.contains(counsellee)){
            throw new RuntimeException("counsellee is not a part of this counselors counsellees");
        }
        counsellee.setCounsellor(null);
        return this.counsellees.remove(counsellee);
    }
    private void removeAllCounsellees(){
        this.counsellees.clear();
    }
    private void setStatus(Status status){
        this.status = status;
    }
    private void setEndDateContract(){
        this.endDateContract = LocalDate.now();
    }
    private void setCounsellor(Employee counsellor) {
        this.counsellor = counsellor;
    }
    private Boolean employeeHasOneYearSeniority(){
        return ChronoUnit.YEARS.between(this.startDateContract, LocalDate.now())>=1?true:false;
    }
    private void setName(String name){
        if(BlankOrEmpty(name)){
            throw new IllegalArgumentException("Cannot be empty or blank");
        }
        this.name = name;
    }
    private void setSurname(String surname){
        if(BlankOrEmpty(surname)){
            throw new IllegalArgumentException("Cannot be empty or blank");
        }
        this.surname = surname;
    }
    private Boolean BlankOrEmpty(String input){
        return (input.isBlank() || input.isBlank())?true:false;
    }

    public long getId() {
        return id;
    }
    public Status getStatus() {
        return status;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public LocalDate getStartDateContract() {
        return startDateContract;
    }
    public LocalDate getEndDateContract() {
        return endDateContract;
    }
    public Unit getUnit() {
        return unit;
    }
    public EnumerationLevel getLevel() {
        return level;
    }
    public Employee getCounsellor() {
        return counsellor;
    }
    public Set<Employee> getCounsellees() {
        return Collections.unmodifiableSet(counsellees);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && status == employee.status && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(dateOfBirth, employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name, surname, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", startDateContract=" + startDateContract +
                ", endDateContract=" + endDateContract +
                ", unit=" + unit +
                ", level=" + level +
                '}';
    }
}
