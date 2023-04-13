package com.example.CounselingTree.services;

import com.example.CounselingTree.controllers.EnumerationLevelController;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.exception.ExistsAlreadyInDatabaseException;
import com.example.CounselingTree.payload.Level;
import com.example.CounselingTree.repositories.EnumerationLevelRepository;
import com.example.CounselingTree.services.interfaces.EnumerationLevelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultEnumerationLevelService implements EnumerationLevelService {
    private EnumerationLevelRepository levelRepository;

    public DefaultEnumerationLevelService(EnumerationLevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<EnumerationLevel> findAll() {
        List<EnumerationLevel> response = levelRepository.findAll();
        if(!response.isEmpty()){
            return response;
        } else{
            throw new NoSuchElementException("No enumerationlevels present");
        }
    }

    @Override
    public Optional<EnumerationLevel> findByDescription(String description) {
        return levelRepository.findByDescription(description);
    }

    @Override
    public Optional<EnumerationLevel> findByCode(String code){
        return levelRepository.findByCode(code);
    }

    @Override
    public void createEnumerationLevel(/*EnumerationLevel*/Level level) {
        if(!levelRepository.findByDescription(level.getDescription()).isPresent()){
            if(!levelRepository.findByCode(level.getCode()).isPresent()){
                levelRepository.save(new EnumerationLevel(level.getCode(), level.getDescription()));
            } else {
                throw new ExistsAlreadyInDatabaseException("Levelcode already exists");
            }
        } else {
            throw new ExistsAlreadyInDatabaseException("Description already exists");
        }
    }
}
