package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.EmployeeAdded;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryAdded extends
        JpaRepository<EmployeeAdded, Integer> {
}
