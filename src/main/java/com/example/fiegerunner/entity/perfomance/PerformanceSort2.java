package com.example.fiegerunner.entity.perfomance;

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
public class PerformanceSort2 {

    Integer qlSort;
    Integer timeSort;

}
