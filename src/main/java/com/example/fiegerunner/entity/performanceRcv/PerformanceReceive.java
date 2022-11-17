package com.example.fiegerunner.entity.performanceRcv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReceive {

    Integer qlReceive;
    Integer timeReceive;
    Integer qlReceiveBoots;
    Integer qlReceiveBoxed;
    Integer qlReceiveHanding;
    Integer qlReceiveShoes;
    Integer qlReceiveOthers;


}
