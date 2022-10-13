package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends
        JpaRepository<Evaluation, Integer> {

}
