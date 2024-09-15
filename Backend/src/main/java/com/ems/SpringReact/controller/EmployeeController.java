package com.ems.SpringReact.controller;
import com.ems.SpringReact.dto.EmployeeDto;
import com.ems.SpringReact.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("")
    public ResponseEntity<EmployeeDto>createEmployee( @RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
   @GetMapping("{id}")
    public ResponseEntity<EmployeeDto>getEmployeeById(@PathVariable("id") Long employeeId) {
       EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
       return  ResponseEntity.ok(employeeDto);
   }
   //Build get All Employees Rest Api
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getAllEmployee()
    {
        List<EmployeeDto> employee=employeeService.getAllEmployees();
        return ResponseEntity.ok(employee);
    }
    //Build update rest Api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee)
    {
        EmployeeDto employeeDto=employeeService.updateEmployee(employeeId,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    //Build Delete Employee Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteEmployee(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");

    }
}
