package com.example.fiegerunner.service;

import com.example.fiegerunner.dto.EmployeeCreateDto;
import com.example.fiegerunner.dto.EmployeeCreateReadDto;
import com.example.fiegerunner.dto.EmployeeReadTLDto;
import com.example.fiegerunner.entity.Employee;
import com.example.fiegerunner.entity.EmployeeRegistered;
import com.example.fiegerunner.mapper.EmployeeCreateMapper;
import com.example.fiegerunner.mapper.EmployeeCreateReadMapper;
import com.example.fiegerunner.repository.EmployeeCreateRepository;
import com.example.fiegerunner.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository repository;
    private final CacheManager cacheManager;
    private final EmployeeCreateRepository employeeCreateRepository;
    private final EmployeeCreateMapper mapperCreate;
    private final EmployeeCreateReadMapper mapperCreateRead;

    @Transactional
    public Employee createEmployee(EmployeeCreateReadDto employeeDto) {
        Employee employee = mapperCreateRead.map(employeeDto);
        return employeeCreateRepository.save(employee);
    }

    public EmployeeCreateReadDto findByExpertis(Integer expertis){
        return mapperCreateRead.reverseMap(employeeCreateRepository.findByExpertis(expertis).get());
    }

    public List<EmployeeCreateReadDto> getAllUsersForTl(Integer expertis){
        return employeeCreateRepository.findAllBySupervisorExpertise(expertis)
                .get()
                .stream()
                .map(mapperCreateRead::reverseMap)
                .collect(Collectors.toList());
    }

    public EmployeeReadTLDto getUserTl(String userName){
        Employee employee = employeeCreateRepository.findByExpertis(repository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + userName)).getExpertis()).orElseThrow();
        return mapperCreateRead.mapToReadTLDto(employee);
    }

    @Override
    @Cacheable(value = "userDetails", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Fetching UserDetails for user: {}", username);
        EmployeeRegistered user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
        UserDetails userDetails = new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
        Objects.requireNonNull(cacheManager.getCache("userDetails")).put(username + "_" + user.getRole(), userDetails);
        return userDetails;
    }

    @CacheEvict(value = "userDetails", key = "#username")
    public void updateUserRole(String username, String newRole) {
        //TODO
    }

    @Transactional
    public EmployeeRegistered createNewUser(EmployeeCreateDto employee) {
        return Optional.of(employee)
                .map(mapperCreate::map).map(repository::save)
                .orElseThrow();
    }

    @Transactional
    public Employee updateEmployee(EmployeeCreateReadDto employee) {
        if (employee != null && employee.expertis() != null) {
            if (employeeCreateRepository.existsByExpertis(employee.expertis())) {
                return employeeCreateRepository.save(mapperCreateRead.map(employee));
            }
        }
        throw new IllegalArgumentException("Invalid employee data");
    }
}
