package com.example.CounselingTree.services;

import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.repositories.UnitRepository;
import com.example.CounselingTree.services.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void createUnit(Unit unit) {
        unitRepository.save(unit);
    }

    @Override
    public List<Unit> findAll(){
        return unitRepository.findAll();
    }
}
