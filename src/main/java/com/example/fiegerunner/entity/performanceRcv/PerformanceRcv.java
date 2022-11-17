package com.example.fiegerunner.entity.performanceRcv;

import com.example.fiegerunner.entity.performancePick.PerformancePick1;
import com.example.fiegerunner.entity.performancePick.PerformanceStow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_receive")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceRcv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate date;

    Integer expertis;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlFastlane" , column = @Column(name = "ql_fastlane")),
            @AttributeOverride(name = "timeFastlane" , column = @Column(name = "time_fastlane"))
    })
    PerformanceFastlane performanceFastlane;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlReplenishment" , column = @Column(name = "ql_replenishment")),
            @AttributeOverride(name = "timeReplenishment" , column = @Column(name = "time_replenishment"))
    })
    PerformanceReplenishment performanceReplenishment;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlRepack" , column = @Column(name = "ql_repack")),
            @AttributeOverride(name = "timeRepack" , column = @Column(name = "time_repack")),
            @AttributeOverride(name = "qlRepackBoots" , column = @Column(name = "ql_repack_boots")),
            @AttributeOverride(name = "qlRepackBoxed" , column = @Column(name = "ql_repack_boxed")),
            @AttributeOverride(name = "qlRepackHanding" , column = @Column(name = "ql_repack_handing")),
            @AttributeOverride(name = "qlRepackShoes" , column = @Column(name = "ql_repack_shoes")),
            @AttributeOverride(name = "qlRepackOthers" , column = @Column(name = "ql_repack_others"))
    })
    PerformanceRepack performanceRepack;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "qlReceive" , column = @Column(name = "ql_receive")),
            @AttributeOverride(name = "timeReceive" , column = @Column(name = "time_receive")),
            @AttributeOverride(name = "qlReceiveBoots" , column = @Column(name = "ql_receive_boots")),
            @AttributeOverride(name = "qlReceiveBoxed" , column = @Column(name = "ql_receive_boxed")),
            @AttributeOverride(name = "qlReceiveHanding" , column = @Column(name = "ql_receive_handing")),
            @AttributeOverride(name = "qlReceiveShoes" , column = @Column(name = "ql_receive_shoes")),
            @AttributeOverride(name = "qlReceiveOthers" , column = @Column(name = "ql_receive_others"))
    })
    PerformanceReceive performanceReceive;

    @Column(name = "time_sup")
    Integer timeSupport;

    @Column(name = "time_ntt")
    Integer timeNtt;
}
