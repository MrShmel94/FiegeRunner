package com.example.fiegerunner.dto;

import com.example.fiegerunner.entity.enums.*;
import lombok.Value;

import java.util.Date;

public record EmployeeCreateReadDto(
        Integer id, Integer expertis, String firstName, String lastName,
                                    String team,
                                    String shift,
                                    String department,
                                    String position,
                                    String gender,
                                    Date dateOfHiring,
                                    Date dateOfDismissal,
                                    Boolean isWorked,
                                    String comment,
                                    Integer supervisorExpertis) {}
