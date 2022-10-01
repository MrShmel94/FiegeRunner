package com.example.fiegerunner.service;

import com.example.fiegerunner.dto.EmployeeAddDto;
import com.example.fiegerunner.dto.EmployeeCreateDto;
import com.example.fiegerunner.entity.EmployeeRegistered;
import com.example.fiegerunner.entity.EmployeeAdded;
import com.example.fiegerunner.mapper.EmployeeAddedMapper;
import com.example.fiegerunner.mapper.EmployeeCreateMapper;
import com.example.fiegerunner.repository.EmployeeRepository;
import com.example.fiegerunner.repository.EmployeeRepositoryAdded;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService implements UserDetailsService {

    private final EmployeeAddedMapper mapper;
    private final EmployeeRepository repository;
    private final EmployeeRepositoryAdded repositoryAdded;
    private final EmployeeCreateMapper mapperCreate;


    @Transactional
    public EmployeeAdded create(EmployeeAddDto employee){
        return Optional.of(employee)
                .map(mapper::map).map(repositoryAdded::save).orElseThrow();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(employee -> new User(
                        employee.getUsername(),
                        employee.getPassword(),
                        Collections.singleton(employee.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }

    @Transactional
    public EmployeeRegistered createNewUser(EmployeeCreateDto employee) {
        return Optional.of(employee)
                .map(mapperCreate::map).map(repository::save)
                .orElseThrow();
    }
}
