package com.example.fiegerunner.mapper;

import com.example.fiegerunner.dto.EmployeeCreateDto;
import com.example.fiegerunner.entity.EmployeeRegistered;
import com.example.fiegerunner.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeCreateMapper implements Mapper<EmployeeCreateDto, EmployeeRegistered> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeRegistered map(EmployeeCreateDto object) {
        EmployeeRegistered employeeRegistered = new EmployeeRegistered();
        employeeRegistered.setExpertis(object.expertis());
        employeeRegistered.setUsername(object.username());
        employeeRegistered.setRole(Role.Storekeeper);
        Optional.ofNullable(
                        object.rawPassword())
                        .filter(StringUtils::hasText)
                        .map(passwordEncoder::encode)
                        .ifPresent(employeeRegistered::setPassword);

     return employeeRegistered;
    }

    @Override
    public EmployeeCreateDto reverseMap(EmployeeRegistered object) {
        return null;
    }
}
