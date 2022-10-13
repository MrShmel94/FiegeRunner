package com.example.fiegerunner.repository;

import com.example.fiegerunner.entity.PerformanceProjection;
import com.example.fiegerunner.entity.perfomance.PerformancePack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface PerformancePackRepository extends JpaRepository<PerformancePack, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT p.expertis,e.last_name lastName, e.first_name firstName, sum(p.ql_opt)  qlOpt, sum(p.time_opt)  timeOpt," +
                    "sum(p.ql_single) qlSingle, sum(p.time_single)  timeSingle," +
                    "sum(p.ql_multi)  qlMulti, sum(p.time_multi)  timeMulti," +
                    "sum(p.ql_sort)  qlSort, sum(p.time_sort)  timeSort," +
                    "sum(p.ql_wmo)  qlWmo, sum(p.time_wmo)  timeWmo " +
                    "FROM performance_pack p " +
                    "JOIN employee e ON p.expertis = e.expertis " +
                    "WHERE date BETWEEN :dateBefore and :dateAfter " +
                    "AND p.expertis IN (:expertis)" +
                    "GROUP BY p.expertis, e.last_name, e.first_name ")
    List<PerformanceProjection> findByAllPerformanceForYourTeam (LocalDate dateBefore, LocalDate dateAfter, Integer... expertis);

    List<PerformancePack> findAllByExpertisAndDateBetween(Integer expertis, LocalDate dateBefore, LocalDate dateAfter);
}
