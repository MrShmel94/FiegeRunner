package com.example.fiegerunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    EmployeeAddRead employeeId;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    EmployeeAddRead supervisorId;

    LocalDate date;

    LocalDate period;

    Double commitment;

    Double policies;

    Double cooperation;
}
