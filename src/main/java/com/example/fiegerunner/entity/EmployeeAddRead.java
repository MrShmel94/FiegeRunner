package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAddRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer expertis;

    String firstName;

    String lastName;

    boolean isWorked;

    @Enumerated(EnumType.STRING)
    Team team;

    @Enumerated(EnumType.STRING)
    Shift shift;

    @Enumerated(EnumType.STRING)
    Department department;

    @Enumerated(EnumType.STRING)
    Position position;

    String comment;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    Employment employment;

    Integer supervisorExpertis;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    List<Evaluation> allEvaluation = new ArrayList<>();

}
