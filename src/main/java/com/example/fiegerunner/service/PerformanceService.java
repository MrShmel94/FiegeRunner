package com.example.fiegerunner.service;

import com.example.fiegerunner.entity.perfomance.PerformanceMulti;
import com.example.fiegerunner.entity.perfomance.PerformanceSort;
import com.example.fiegerunner.entity.perfomance.PerformanceSorter;
import com.example.fiegerunner.entity.perfomance.PerformanceSingle;
import com.example.fiegerunner.repository.PerformanceRepositoryMulti;
import com.example.fiegerunner.repository.PerformanceRepositorySort;
import com.example.fiegerunner.repository.PerformanceRepositorySorter;
import com.example.fiegerunner.repository.PerformanceRepositorySingle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerformanceService {

    private final PerformanceRepositorySorter performanceRepositorySorter;
    private final PerformanceRepositorySingle performanceRepositorySingle;
    private final PerformanceRepositoryMulti performanceRepositoryMulti;
    private final PerformanceRepositorySort performanceRepositorySort;

    public void savePerformance(List<PerformanceSorter> performanceSorterList){
       performanceRepositorySorter.saveAll(performanceSorterList);
    }

    public void savePerformanceSingle(List<PerformanceSingle> performanceList){
        performanceRepositorySingle.saveAll(performanceList);
    }

    public void savePerformanceMulti(List<PerformanceMulti> performanceList){
        performanceRepositoryMulti.saveAll(performanceList);
    }

    public void savePerformanceSort(List<PerformanceSort> performanceList){
        performanceRepositorySort.saveAll(performanceList);
    }
}
