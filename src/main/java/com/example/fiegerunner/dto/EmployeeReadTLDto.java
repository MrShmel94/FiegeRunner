package com.example.fiegerunner.dto;

import lombok.Builder;
import lombok.Value;

@Builder
public record EmployeeReadTLDto(Integer id, Integer expertis, String firstName,
                                String lastName, String position) {

}
