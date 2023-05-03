package com.example.fiegerunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "daily_logs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "items_packed", nullable = false)
    private Integer itemsPacked;

    @Column(name = "items_packed_boots", nullable = false)
    private Integer itemsPackedBoots;

    @Column(name = "time_spent", nullable = false)
    private Integer timeSpent;

}
