package com.example.fiegerunner.entity.performancePick;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_pick")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformancePick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate date;

    Integer expertis;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlStow" , column = @Column(name = "ql_stow")),
            @AttributeOverride(name = "timeStow" , column = @Column(name = "time_stow"))
    })
    PerformanceStow performanceStow;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlPick" , column = @Column(name = "ql_pick")),
            @AttributeOverride(name = "timePick" , column = @Column(name = "time_pick"))
    })
    PerformancePick1 performancePick;

    @Column(name = "time_sup")
    Integer timeSupport;

    @Column(name = "ntt_time")
    Integer timeNtt;
}
