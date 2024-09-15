package com.ems.SpringReact.service;

import com.ems.SpringReact.Entity.Employee;
import com.ems.SpringReact.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId,EmployeeDto updatedEmployee);
     void deleteEmployee(Long employeeId);
}
