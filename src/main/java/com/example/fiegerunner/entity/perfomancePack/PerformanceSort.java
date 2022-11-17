package com.example.fiegerunner.entity.perfomancePack;

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
public class PerformanceSort {

    Integer qlSort;
    Integer timeSort;

}
