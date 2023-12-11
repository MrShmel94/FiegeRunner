package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.convert.ProcessDetailListJsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    @Column(name = "employee_expertis")
    private Integer employeeExpertis;

    @Convert(converter = ProcessDetailListJsonConverter.class)
    @Column(name = "daily_log_details")
    private List<ProcessDetail> dailyLogDetails;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time_spent_support", nullable = false)
    private Integer timeSpentSupport;

    @Column(name = "time_spent_ntt", nullable = false)
    private Integer timeSpentNtt;

}
