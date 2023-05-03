package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.Department;
import com.example.fiegerunner.entity.Shift;
import com.example.fiegerunner.entity.Team;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByName(String name);
    @NotNull Optional<Team> findById(@NotNull Integer id);

}
