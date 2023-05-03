package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.enums.ProcessNameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "processes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "process_name", nullable = false)
    private String processName;

    @Column(name = "process_need_performance", nullable = false)
    private Integer processNeedPerformance;

    @OneToMany(mappedBy = "process")
    private Set<DailyLog> dailyLogs;

    @ManyToMany(mappedBy = "processes")
    private Set<Department> departments;
}
