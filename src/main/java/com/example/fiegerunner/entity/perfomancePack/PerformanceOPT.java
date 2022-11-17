package com.example.fiegerunner.entity.perfomancePack;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceOPT {

    Integer qlOpt;
    Integer timeOpt;

}
