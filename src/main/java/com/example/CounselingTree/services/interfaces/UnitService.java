package com.example.CounselingTree.services.interfaces;

import com.example.CounselingTree.entities.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    Optional<Unit> findByName(String name);
    void createUnit(String unitname);
    List<Unit> findAll();

}
