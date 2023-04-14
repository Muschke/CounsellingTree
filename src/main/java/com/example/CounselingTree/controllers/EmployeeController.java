package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.CounsellorAndCounsellee;
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

    //setCounsellor -- untested method
    @PostMapping("/setCounsellor")
    public ResponseEntity<String> setCounsellorForEmployee(@RequestBody CounsellorAndCounsellee counsellorAndCounsellee){
        employeeService.setCounsellorForEmployee(counsellorAndCounsellee);
        return ResponseEntity.ok("Counsellor added to employee succesfully");
    }

    //addCounselleeXToCounsellorY  -- untested method
    @PostMapping("/addCounsellee")
    public ResponseEntity<String> addCounseleeToCounselor(@RequestBody CounsellorAndCounsellee counsellorAndCounsellee){
        employeeService.addCounseleeToCounselor(counsellorAndCounsellee);
        return ResponseEntity.ok("Counsellee added to counsellor succesfully");
    }

    //removeCounsellee
    @PostMapping("/removeCounsellee")
    public ResponseEntity<String> removeCounseleeFromCounselor(@RequestBody CounsellorAndCounsellee counsellorAndCounsellee){
        employeeService.removeCounseleeFromCounselor(counsellorAndCounsellee);
        return ResponseEntity.ok("Counsellee removed to counsellor succesfully");
    }

    //fireAnEmployee
    @GetMapping("/terminateContract")
    public ResponseEntity<String> terminateContract(@RequestBody long id){
        employeeService.terminateContract(id);
        return ResponseEntity.ok("Contract of counsellor terminated, counsellees removed");
    }
}
