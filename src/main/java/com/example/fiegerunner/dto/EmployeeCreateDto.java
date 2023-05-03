package com.example.fiegerunner.dto;

import com.example.fiegerunner.entity.enums.Role;
import lombok.Value;

public record EmployeeCreateDto(String username, Integer expertis,
                                Role role, String rawPassword) {

}
