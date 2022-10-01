package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.EmployeeRegistered;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends
        JpaRepository<EmployeeRegistered, Integer> {

    Optional<EmployeeRegistered> findByUsername(String username);
}
