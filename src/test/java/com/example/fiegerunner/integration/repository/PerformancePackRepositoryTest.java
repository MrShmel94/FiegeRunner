package com.example.fiegerunner.integration.repository;

import com.example.fiegerunner.integration.annotation.IT;
import com.example.fiegerunner.repository.PerformancePackRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@IT
@RequiredArgsConstructor
public class PerformancePackRepositoryTest {

    private final PerformancePackRepository repository;

    @Test
    void check(){
        var byId = repository.findById(1);
        System.out.println(byId);
    }

    @Test
    void checkFind(){
        var result = repository.findBy(
                LocalDate.of(2022,8, 1), LocalDate.of(2022, 9,30), 304917,3301837
        );
        System.out.println();
        result.forEach(System.out::println);
    }
//    @Test
//    void checkqwe(){
//        var result = repository.findBy();
//        System.out.println(result);
//    }
}
