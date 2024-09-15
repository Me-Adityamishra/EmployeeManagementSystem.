package com.ems.SpringReact.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeDto {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public EmployeeDto(Long id,String firstName,String lastName, String email)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }
    public EmployeeDto()
    {
        super();
    }
}
