package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.perfomance.PerformanceSingle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepositorySingle extends JpaRepository< PerformanceSingle, Integer> {
}
