package com.example.fiegerunner.entity.performancePick;

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
public class PerformancePick1 {

    Integer qlPick;
    Integer timePick;

}
