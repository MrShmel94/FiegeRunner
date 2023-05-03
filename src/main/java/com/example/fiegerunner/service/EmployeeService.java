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

    //private final EmployeeAddReadMapper mapper;
    private final EmployeeRepository repository;
    private final CacheManager cacheManager;
    private final EmployeeCreateRepository employeeCreateRepository;
   // private final EmployeeRepositoryAdded repositoryAdded;
    //private final PerformancePackRepository performancePackRepository;
    private final EmployeeCreateMapper mapperCreate;
    private final EmployeeCreateReadMapper mapperCreateRead;
    //private final PerformancePackMapper performancePackMapper;
   // private final EmployeeReadTLMapper tlMapper;

    @Transactional
    public Employee createEmployee(EmployeeCreateReadDto employeeDto) {
        Employee employee = mapperCreateRead.map(employeeDto);
        return employeeCreateRepository.save(employee);
    }

    public EmployeeCreateReadDto findByExpertis(Integer expertis){
        return mapperCreateRead.reverseMap(employeeCreateRepository.findByExpertise(expertis).get());
    }

//    @Transactional
//    public EmployeeAddRead create(EmployeeAddReadDto employee) {
//        return Optional.of(employee)
//                .map(mapper::map).map(repositoryAdded::save).orElseThrow();
//    }

//    @PreAuthorize("hasAnyRole('Admin', 'TeamLead', 'AreaManager')")
//    public List<PerformanceDto> getAllPerformanceForEmployee(
//            String userName, LocalDate dateBefore, LocalDate dateAfter
//    ) {
//        var maybeAllExpertis = findAllExpertisByTeam(userName);
//        if (!maybeAllExpertis.isEmpty()) {
//            var byAllPerformanceForYourTeam = performancePackRepository.findByAllPerformanceForYourTeam(
//                    dateBefore, dateAfter, maybeAllExpertis.toArray(new Integer[0])
//            );
//            return byAllPerformanceForYourTeam.stream().map(performancePackMapper::map).collect(Collectors.toList());
//        }
//        return new ArrayList<>();
//    }
//
//    public List<EmployeeAddRead> findAllEmployeeBySupervisorExpertis() {
//        return new ArrayList<>();
//    }
//
//    public List<EmployeeReadTLDto> allEmployeeByTeam(String userName) {
//        var maybeAllExpertis = findAllExpertisByTeam(userName);
//        if (!maybeAllExpertis.isEmpty()) {
//            return repositoryAdded.findAllEmployeeByTeam(maybeAllExpertis.toArray(new Integer[0])).stream()
//                    .map(tlMapper::map).collect(Collectors.toList());
//        }
//        return new ArrayList<>();
//    }

    public List<EmployeeCreateReadDto> getAllUsersForTl(Integer expertis){
        return employeeCreateRepository.findAllBySupervisorExpertise(expertis)
                .get()
                .stream()
                .map(mapperCreateRead::reverseMap)
                .collect(Collectors.toList());
    }

    public EmployeeReadTLDto getUserTl(String userName){
        Employee employee = employeeCreateRepository.findByExpertise(repository.findByUsername(userName)
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
        // Your existing implementation
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
            if (employeeCreateRepository.existsByExpertise(employee.expertis())) {
                return employeeCreateRepository.save(mapperCreateRead.map(employee));
            }
        }
        throw new IllegalArgumentException("Invalid employee data");
    }
//    private List<Integer> findAllExpertisByTeam(String userName) {
//        var mayBeUser = repository.findByUsername(userName);
//        if (mayBeUser.isPresent()) {
//            var mayBeExpertis = repositoryAdded.findByExpertis(mayBeUser.get().getExpertis());
//            if (mayBeExpertis.isPresent()) {
//                var employeeAddRead = mayBeExpertis.get();
//                return repositoryAdded.findAllExpertisByTeam(employeeAddRead.getTeam().name(),
//                        employeeAddRead.getShift().name(), employeeAddRead.getDepartment().name());
//            }
//        }
//        return new ArrayList<>();
//    }
}
