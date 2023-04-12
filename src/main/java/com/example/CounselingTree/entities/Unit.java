package com.example.CounselingTree.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "units")
public class Unit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    public Unit(String name) {
        setName(name);
    }

    protected Unit (){}

    public long getId() {
        return id;
    }

    private void setName(String name){
        if(name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("Unitname cannot be blank or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(name, unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
