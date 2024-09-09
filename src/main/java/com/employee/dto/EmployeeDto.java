package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EmployeeDto implements Serializable {
    private Long id;

    @NotEmpty
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    @NotBlank
    private String salary;

    @NotNull
    private Date joinDate;

    @NotBlank
    private String departement;
}
