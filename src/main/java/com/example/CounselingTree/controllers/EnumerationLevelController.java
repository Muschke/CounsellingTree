package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.payload.Level;
import com.example.CounselingTree.services.interfaces.EnumerationLevelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levels")
public class EnumerationLevelController {
    private EnumerationLevelService levelService;

    public EnumerationLevelController(EnumerationLevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<EnumerationLevel>> getAllUnits(){
        return ResponseEntity.ok(levelService.findAll());
    }

    @PostMapping("/addLevel")
    public ResponseEntity<String> addNewLevel(@RequestBody /*EnumerationLevel*/Level level){
        levelService.createEnumerationLevel(level);
        return ResponseEntity.ok("New level added succesfully");
    }

}
