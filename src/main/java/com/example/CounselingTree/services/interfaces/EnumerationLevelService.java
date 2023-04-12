package com.example.CounselingTree.services.interfaces;

import com.example.CounselingTree.entities.EnumerationLevel;

import java.util.List;
import java.util.Optional;

public interface EnumerationLevelService {
    List<EnumerationLevel> findAll();
    Optional<EnumerationLevel> findByDescription(String description);
    void createEnumerationLevel(EnumerationLevel enumerationLevel);
}
