package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.perfomance.PerformanceMulti;
import com.example.fiegerunner.entity.perfomance.PerformanceSorter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepositoryMulti extends JpaRepository<PerformanceMulti, Integer> {

}
