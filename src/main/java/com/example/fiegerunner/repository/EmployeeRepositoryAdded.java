package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.EmployeeAddRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryAdded extends
        JpaRepository<EmployeeAddRead, Integer> {

    Optional<EmployeeAddRead> findByExpertis(Integer expertis);

    @Query(
            nativeQuery = true,
            value = """
SELECT expertis FROM employee
WHERE team = :team
AND shift = :shift
AND department = :department
"""
    )
    List<Integer> findAllExpertisByTeam(String team, String shift, String department);
}
