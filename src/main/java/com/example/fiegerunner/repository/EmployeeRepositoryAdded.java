//package com.example.fiegerunner.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface EmployeeRepositoryAdded extends
//        JpaRepository<EmployeeAddRead, Integer> {
//
//    Optional<EmployeeAddRead> findById(Integer id);
//
//    @Query(
//            nativeQuery = true,
//            value = """
//SELECT *
//FROM employee
//WHERE expertis IN (:expertis)
//"""
//    )
//    List<EmployeeAddRead> findAllEmployeeByTeam(Integer ... expertis);
//
//    Optional<EmployeeAddRead> findByExpertis(Integer expertis);
//
//    @Query(
//            nativeQuery = true,
//            value = """
//SELECT expertis FROM employee
//WHERE team = :team
//AND shift = :shift
//AND department = :department
//"""
//    )
//    List<Integer> findAllExpertisByTeam(String team, String shift, String department);
//
//    List<EmployeeAddRead> findAllBySupervisorExpertis(Integer supervisorExpertis);
//}
