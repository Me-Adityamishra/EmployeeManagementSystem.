package com.ems.SpringReact.Impl;

import com.ems.SpringReact.Entity.Employee;
import com.ems.SpringReact.dto.EmployeeDto;
import com.ems.SpringReact.exception.ResourseNotFoundException;
import com.ems.SpringReact.mapper.EmployeeMapper;
import com.ems.SpringReact.repository.EmployeeRepository;
import com.ems.SpringReact.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceimp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceimp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
       Employee savedEmployee =employeeRepository.save(employee);
       return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
     Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourseNotFoundException("Employee is not exist by this id "+employeeId));
      return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
       Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResourseNotFoundException("Employee is not exist with given id:"+employeeId));
       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setLastName(updatedEmployee.getLastName());
       employee.setEmail(updatedEmployee.getEmail());
      Employee updatedEmployeeObj= employeeRepository.save(employee);
      return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResourseNotFoundException("Employee is not exist with given id:"+employeeId));
          employeeRepository.deleteById(employeeId);
    }

}
