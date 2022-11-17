package com.example.fiegerunner.entity.perfomancePack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_pack")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformancePack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate date;

    Integer expertis;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlOpt" , column = @Column(name = "ql_opt")),
            @AttributeOverride(name = "timeOpt" , column = @Column(name = "time_opt"))
    })
    PerformanceOPT performanceOPT;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlSingle" , column = @Column(name = "ql_single")),
            @AttributeOverride(name = "timeSingle" , column = @Column(name = "time_single"))
    })
    PerformanceSingle performanceSingle;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlMulti" , column = @Column(name = "ql_multi")),
            @AttributeOverride(name = "timeMulti" , column = @Column(name = "time_multi"))
    })
    PerformanceMulti performanceMulti;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlSort" , column = @Column(name = "ql_sort")),
            @AttributeOverride(name = "timeSort" , column = @Column(name = "time_sort"))
    })
    PerformanceSort performanceSort;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlWmo" , column = @Column(name = "ql_wmo")),
            @AttributeOverride(name = "timeWmo" , column = @Column(name = "time_wmo"))
    })
    PerformanceWmo performanceWmo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "timeSupportOptimus" , column = @Column(name = "time_sup_opt")),
            @AttributeOverride(name = "timeSupportManual" , column = @Column(name = "time_sup_manual")),
            @AttributeOverride(name = "timeNttPack" , column = @Column(name = "ntt_time"))
    })
    PerformanceSupportNttPack performanceSupportNttPack;

}
