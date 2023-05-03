package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.Department;
import com.example.fiegerunner.entity.Position;
import com.example.fiegerunner.entity.Shift;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    Optional<Shift> findByName(String name);
    @NotNull Optional<Shift> findById(@NotNull Integer id);

}
