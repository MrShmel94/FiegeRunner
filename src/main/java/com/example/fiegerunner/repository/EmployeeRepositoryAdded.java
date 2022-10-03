package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.EmployeeAddRead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryAdded extends
        JpaRepository<EmployeeAddRead, Integer> {
}
