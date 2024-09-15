package com.ems.SpringReact.repository;

import com.ems.SpringReact.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
