package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.Department;
import com.example.fiegerunner.entity.Position;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByName(String name);
    @NotNull Optional<Department> findById(@NotNull Integer id);
}
