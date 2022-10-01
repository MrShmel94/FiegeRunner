package com.example.fiegerunner.dto;

import com.example.fiegerunner.entity.enums.*;
import lombok.Value;

@Value
public class EmployeeReadDto {

    Integer expertis;
    String firstName;
    String lastName;
    Team team;
    Shift shift;
    Department department;
    Position position;
    Gender gender;
    String fullNameSupervisor;

}
