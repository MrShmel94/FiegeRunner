package com.example.fiegerunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_attendance")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "attendance_status", nullable = false)
    private String attendanceStatus;
}
