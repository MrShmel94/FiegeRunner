package com.example.fiegerunner.controller;

import com.example.fiegerunner.config.JwtUtils;
import com.example.fiegerunner.dto.AdditionalInfoForCreateEmployee;
import com.example.fiegerunner.dto.EmployeeCreateReadDto;
import com.example.fiegerunner.dto.EmployeeLoginDto;
import com.example.fiegerunner.entity.Employee;
import com.example.fiegerunner.entity.Shift;
import com.example.fiegerunner.entity.enums.DepartmentEnum;
import com.example.fiegerunner.entity.enums.PositionEnum;
import com.example.fiegerunner.entity.enums.ShiftEnum;
import com.example.fiegerunner.entity.enums.TeamEnum;
import com.example.fiegerunner.exeptions.UserNotFoundException;
import com.example.fiegerunner.mapper.EmployeeCreateReadMapper;
import com.example.fiegerunner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final EmployeeService employeeService;
    private final JwtUtils jwtUtils;

    @GetMapping("/allUsers")
    public ResponseEntity<List<EmployeeCreateReadDto>> getAllUser(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(employeeService.getAllUsersForTl(jwtUtils.getExpertisFromToken(authHeader)));
    }

    @GetMapping("/createEmployee")
    public ResponseEntity<?> additionalInfoAboutCreate() {
        return ResponseEntity.ok(new AdditionalInfoForCreateEmployee(
                DepartmentEnum.asList(),
                PositionEnum.asList(),
                TeamEnum.asList(),
                ShiftEnum.asList()
        ));
    }

    @PostMapping("/createEmployee")
    @PreAuthorize("hasAnyRole('Admin', 'TeamLead', 'AreaManager')")
    public ResponseEntity<?> createNewEmployee(@RequestBody EmployeeCreateReadDto employeeDto) {
        Employee createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping("/employeeByExpertise/{expertis}")
    @PreAuthorize("hasAnyRole('Admin', 'TeamLead', 'AreaManager')")
    public ResponseEntity<?> employeeFindByExpertis(@PathVariable Integer expertis) {
        return ResponseEntity.ok(employeeService.findByExpertis(expertis));
    }

    @PutMapping("/updateEmployee")
    @PreAuthorize("hasAnyRole('Admin', 'TeamLead', 'AreaManager')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeCreateReadDto employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
