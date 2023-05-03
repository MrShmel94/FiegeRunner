//package com.example.fiegerunner.repository;
//
//import com.example.fiegerunner.entity.PerformanceProjection;
//import com.example.fiegerunner.entity.PerformanceProjectionFull;
//import com.example.fiegerunner.entity.perfomancePack.PerformancePack;
//import com.example.fiegerunner.entity.perfomancePack.PerformanceWmo;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface PerformancePackRepository extends JpaRepository<PerformancePack, Integer> {
//
//
//    @Query(nativeQuery = true,
//            value = "SELECT p.expertis,e.last_name lastName, e.first_name firstName, sum(p.ql_opt)  qlOpt, sum(p.time_opt)  timeOpt," +
//                    "sum(p.ql_single) qlSingle, sum(p.time_single)  timeSingle," +
//                    "sum(p.ql_multi)  qlMulti, sum(p.time_multi)  timeMulti," +
//                    "sum(p.ql_sort)  qlSort, sum(p.time_sort)  timeSort," +
//                    "sum(p.ql_wmo)  qlWmo, sum(p.time_wmo)  timeWmo " +
//                    "FROM performance_pack p " +
//                    "JOIN employee e ON p.expertis = e.expertis " +
//                    "WHERE date BETWEEN :dateBefore and :dateAfter " +
//                    "AND p.expertis IN (:expertis)" +
//                    "GROUP BY p.expertis, e.last_name, e.first_name ")
//    List<PerformanceProjection> findByAllPerformanceForYourTeam (LocalDate dateBefore, LocalDate dateAfter, Integer... expertis);
//
//    @Query(nativeQuery = true,
//            value = "SELECT p.expertis,e.last_name lastName, e.first_name firstName, sum(p.ql_opt)  qlOpt, sum(p.time_opt)  timeOpt," +
//                    "sum(p.ql_single) qlSingle, sum(p.time_single)  timeSingle," +
//                    "sum(p.ql_multi)  qlMulti, sum(p.time_multi)  timeMulti," +
//                    "sum(p.ql_sort)  qlSort, sum(p.time_sort)  timeSort," +
//                    "sum(p.ql_wmo)  qlWmo, sum(p.time_wmo)  timeWmo, " +
//                    "sum(p.time_sup_manual) timeSupportManual, sum(p.time_sup_opt) timeSupportOpt, " +
//                    "sum(pk.ql_pick) qlPick, sum(pk.time_pick) timePick, " +
//                    "sum(pk.ql_stow) qlStow, sum(pk.time_stow) timeStow, " +
//                    "sum(pk.time_sup) timeSupportPick, sum(pk.ntt_time) timeNttPick," +
//                    "sum(rc.ql_fastlane) qlFastlane, sum(rc.time_fastlane) timeFastlane, " +
//                    "sum(rc.ql_replenishment) qlReplenishment, sum(rc.time_replenishment) timeReplenishment, " +
//                    "sum(rc.ql_repack) qlRepack, sum(rc.time_repack) timeRepack, " +
//                    "sum(rc.ql_repack_boots) qlRepackBoots, sum(rc.ql_repack_boxed) qlRepackBoxed, " +
//                    "sum(rc.ql_repack_handing) qlRepackHanding, sum(rc.ql_repack_shoes) qlRepackShoes, " +
//                    "sum(rc.ql_repack_others) qlRepackOthers, sum(rc.ql_receive) qlReceive, " +
//                    "sum(rc.time_receive) timeReceive , sum(rc.ql_receive_boots) qlReceiveBoots, " +
//                    "sum(rc.ql_receive_boxed) qlReceiveBoxed, sum(rc.ql_receive_handing) qlReceiveHanding, " +
//                    "sum(rc.ql_receive_shoes) qlReceiveShoes, sum(rc.ql_receive_others) qlReceiveOthers, " +
//                    "sum(rc.time_sup) timeSupportReceive , sum(rc.time_ntt) timeNttReceive " +
//                    "FROM performance_pack p " +
//                    "JOIN employee e ON p.expertis = e.expertis " +
//                    "JOIN performance_pick pk ON p.expertis = pk.expertis " +
//                    "JOIN performance_receive rc on p.expertis = rc.expertis " +
//                    "WHERE p.date BETWEEN :dateBefore and :dateAfter " +
//                    "AND pk.date BETWEEN :dateBefore and :dateAfter " +
//                    "AND rc.date BETWEEN :dateBefore and :dateAfter " +
//                    "AND p.expertis IN (:expertis) " +
//                    "GROUP BY p.expertis, e.last_name, e.first_name ")
//    List<PerformanceProjectionFull> findByAllPerformanceForYourTeamFull (LocalDate dateBefore, LocalDate dateAfter, Integer... expertis);
//
//    List<PerformancePack> findAllByExpertisAndDateBetween(Integer expertis, LocalDate dateBefore, LocalDate dateAfter);
//}
