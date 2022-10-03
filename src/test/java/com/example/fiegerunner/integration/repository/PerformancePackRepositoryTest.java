package com.example.fiegerunner.integration.repository;

import com.example.fiegerunner.entity.EmployeeAddRead;
import com.example.fiegerunner.entity.EmployeeRegistered;
import com.example.fiegerunner.integration.annotation.IT;
import com.example.fiegerunner.repository.EmployeeRepository;
import com.example.fiegerunner.repository.EmployeeRepositoryAdded;
import com.example.fiegerunner.repository.PerformancePackRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@IT
@RequiredArgsConstructor
public class PerformancePackRepositoryTest {

    private final PerformancePackRepository repository;
    private final EmployeeRepository repositoryEmp;
    private final EmployeeRepositoryAdded repositoryAdded;

    @Test
    void check(){
        var byId = repository.findById(1);
        System.out.println(byId);
    }

    @Test
    void checkFind(){
        List<Integer> array = List.of(304192,302158);
        var result = repository.findByAllPerformanceForYourTeam(
                LocalDate.of(2022,8, 1), LocalDate.of(2022, 9,30), array.toArray(new Integer[0])
        );

        System.out.println();
        result.forEach(System.out::println);
    }

    @Test
    void checkEmplRep(){
        var mayBeUsername = repositoryEmp.findByUsername("test@gmail.ru");
        System.out.println();
    }

    @Test
    void checkExpertis(){
        var byExpertis = repositoryAdded.findByExpertis(304573);
        System.out.println();
    }

}
