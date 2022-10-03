package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.PerformanceProjection;
import com.example.fiegerunner.entity.perfomance.PerformancePack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerformancePackRepository extends JpaRepository<PerformancePack, Integer> {

    @Query(nativeQuery = true,
    value = "SELECT expertis, sum(ql_opt)  qlOpt, sum(time_opt)  timeOpt," +
            "sum(ql_single) qlSingle, sum(time_single)  timeSingle," +
            "sum(ql_multi)  qlMulti, sum(time_multi)  timeMulti," +
            "sum(ql_sort)  qlSort, sum(time_sort)  timeSort," +
            "sum(ql_wmo)  qlWmo, sum(time_wmo)  timeWmo " +
            "FROM performance_pack " +
            "WHERE date BETWEEN :dateBefore and :dateAfter " +
            "AND expertis IN (:inExpertis)" +
            "GROUP BY expertis ")
    List<PerformanceProjection> findBy (LocalDate dateBefore, LocalDate dateAfter, Integer...inExpertis);

}
