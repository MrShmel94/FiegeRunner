package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeCreateRepository extends
        JpaRepository<Employee, Integer> {

    Optional<Employee> findByExpertis (Integer expertis);
    boolean existsByExpertis (Integer expertis);
    Optional<List<Employee>> findAllBySupervisorExpertise (Integer expertis);
}
