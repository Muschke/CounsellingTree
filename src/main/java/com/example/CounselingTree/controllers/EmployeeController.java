package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.EmployeeDto;
import com.example.CounselingTree.payload.Level;
import com.example.CounselingTree.services.interfaces.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Employee>> getAllUnits(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addNewEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok("New employee added succesfully");
    }
}
