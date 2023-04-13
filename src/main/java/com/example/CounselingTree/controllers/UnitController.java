package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.exception.ExistsAlreadyInDatabaseException;
import com.example.CounselingTree.services.interfaces.UnitService;
import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Unit>> getAllUnits(){
        return ResponseEntity.ok(unitService.findAll());
    }

    @PostMapping("/addUnit")
    public ResponseEntity<String> addNewUnit(@RequestBody String unitname){
            unitService.createUnit(unitname);
            return ResponseEntity.ok("New unit added succesfully");
    }
}
