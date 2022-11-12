package com.example.fiegerunner.service;

import com.example.fiegerunner.dto.EmployeeAddReadDto;
import com.example.fiegerunner.dto.EmployeeCreateDto;
import com.example.fiegerunner.dto.EmployeeReadTLDto;
import com.example.fiegerunner.dto.PerformanceDto;
import com.example.fiegerunner.entity.EmployeeRegistered;
import com.example.fiegerunner.entity.EmployeeAddRead;
import com.example.fiegerunner.mapper.EmployeeAddReadMapper;
import com.example.fiegerunner.mapper.EmployeeCreateMapper;
import com.example.fiegerunner.mapper.EmployeeReadTLMapper;
import com.example.fiegerunner.mapper.PerformancePackMapper;
import com.example.fiegerunner.repository.EmployeeRepository;
import com.example.fiegerunner.repository.EmployeeRepositoryAdded;
import com.example.fiegerunner.repository.PerformancePackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService implements UserDetailsService {

    private final EmployeeAddReadMapper mapper;
    private final EmployeeRepository repository;
    private final EmployeeRepositoryAdded repositoryAdded;
    private final PerformancePackRepository performancePackRepository;
    private final EmployeeCreateMapper mapperCreate;
    private final PerformancePackMapper performancePackMapper;
    private final EmployeeReadTLMapper tlMapper;


    @Transactional
    public EmployeeAddRead create(EmployeeAddReadDto employee) {
        return Optional.of(employee)
                .map(mapper::map).map(repositoryAdded::save).orElseThrow();
    }

    @PreAuthorize("hasAnyRole('Admin', 'TeamLead', 'AreaManager')")
    public List<PerformanceDto> getAllPerformanceForEmployee(
            String userName, LocalDate dateBefore, LocalDate dateAfter
    ) {
        var maybeAllExpertis = findAllExpertisByTeam(userName);
        if (!maybeAllExpertis.isEmpty()) {
            var byAllPerformanceForYourTeam = performancePackRepository.findByAllPerformanceForYourTeam(
                    dateBefore, dateAfter, maybeAllExpertis.toArray(new Integer[0])
            );
            return byAllPerformanceForYourTeam.stream().map(performancePackMapper::map).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<EmployeeAddRead> findAllEmployeeBySupervisorExpertis() {
        return new ArrayList<>();
    }

    public List<EmployeeReadTLDto> allEmployeeByTeam(String userName) {
        var maybeAllExpertis = findAllExpertisByTeam(userName);
        if (!maybeAllExpertis.isEmpty()) {
            return repositoryAdded.findAllEmployeeByTeam(maybeAllExpertis.toArray(new Integer[0])).stream()
                    .map(tlMapper::map).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username {}", username);
        System.out.println(username);
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

    private List<Integer> findAllExpertisByTeam(String userName) {
        var mayBeUser = repository.findByUsername(userName);
        if (mayBeUser.isPresent()) {
            var mayBeExpertis = repositoryAdded.findByExpertis(mayBeUser.get().getExpertis());
            if (mayBeExpertis.isPresent()) {
                var employeeAddRead = mayBeExpertis.get();
                return repositoryAdded.findAllExpertisByTeam(employeeAddRead.getTeam().name(),
                        employeeAddRead.getShift().name(), employeeAddRead.getDepartment().name());
            }
        }
        return new ArrayList<>();
    }
}
