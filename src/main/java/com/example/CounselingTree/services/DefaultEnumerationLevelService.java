package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.repositories.EnumerationLevelRepository;
import com.example.CounselingTree.services.interfaces.EnumerationLevelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultEnumerationLevelService implements EnumerationLevelService {
    private EnumerationLevelRepository levelRepository;

    public DefaultEnumerationLevelService(EnumerationLevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<EnumerationLevel> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<EnumerationLevel> findByDescription(String description) {
        return levelRepository.findByDescription(description);
    }

    @Override
    public void createEnumerationLevel(EnumerationLevel enumerationLevel) {
        levelRepository.save(enumerationLevel);
    }
}
