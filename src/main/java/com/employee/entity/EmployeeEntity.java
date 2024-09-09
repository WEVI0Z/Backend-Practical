package com.employee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeEntity implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long salary;
    private Date joinDate;
    private String departement;
}
