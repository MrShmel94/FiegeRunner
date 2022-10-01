package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.perfomance.PerformanceSorter;
import com.example.fiegerunner.entity.perfomance.PerformanceWmo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepositoryWmo extends JpaRepository<PerformanceWmo, Integer> {

}
