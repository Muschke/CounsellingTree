package com.example.CounselingTree.services.interfaces;

import com.example.CounselingTree.controllers.EnumerationLevelController;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.Level;

import java.util.List;
import java.util.Optional;

public interface EnumerationLevelService {
    List<EnumerationLevel> findAll();
    Optional<EnumerationLevel> findByDescription(String description);
    Optional<EnumerationLevel> findByCode(String code);
    void createEnumerationLevel(/*EnumerationLevel*/Level enumerationLevel);
}
