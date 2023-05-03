package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.Department;
import com.example.fiegerunner.entity.Position;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findByName(String name);
    @NotNull Optional<Position> findById(@NotNull Integer id);


}
