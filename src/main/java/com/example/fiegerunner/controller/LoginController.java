package com.example.fiegerunner.controller;

import com.example.fiegerunner.Utils.JwtResponse;
import com.example.fiegerunner.config.JwtUtils;
import com.example.fiegerunner.dto.EmployeeCreateDto;
import com.example.fiegerunner.dto.EmployeeLoginDto;
import com.example.fiegerunner.exeptions.UserNotFoundException;
import com.example.fiegerunner.mapper.EmployeeCreateMapper;
import com.example.fiegerunner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final EmployeeService service;
    private final JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeLoginDto loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.username(), loginRequest.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateToken(userDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("jwtToken", jwtToken);
            response.put("user", service.getUserTl(userDetails.getUsername()));
            return ResponseEntity.ok(response);
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EmployeeCreateDto registeredRequest) {

        service.createNewUser(registeredRequest);

        try {
            return ResponseEntity.ok("Logged in successfully");
        }
        catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
