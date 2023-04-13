package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.exception.ExistsAlreadyInDatabaseException;
import com.example.CounselingTree.repositories.UnitRepository;
import com.example.CounselingTree.services.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultUnitService implements UnitService {
    private UnitRepository unitRepository;

    public DefaultUnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Optional<Unit> findByName(String name) {
        return unitRepository.findByName(name);
    }

    @Override
    public void createUnit(String unitname) {
        if(!unitRepository.findByName(unitname).isPresent()){
            unitRepository.save(new Unit(unitname));
        } else {
            throw new ExistsAlreadyInDatabaseException("Unit already exists");
        }
    }

    @Override
    public List<Unit> findAll(){
        if(!unitRepository.findAll().isEmpty()){
            return unitRepository.findAll();
        } else{
            throw new NoSuchElementException("No units present");
        }

    }
}
