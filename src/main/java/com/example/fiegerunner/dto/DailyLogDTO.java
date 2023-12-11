package com.example.fiegerunner.dto;

import com.example.fiegerunner.entity.ProcessDetail;

import java.time.LocalDate;
import java.util.List;

public record DailyLogDTO (
        Integer id,
        Integer employeeExpertis,
        List<ProcessDetail> dailyLogDetails,
        LocalDate date,
        Integer timeSpentSupport,
        Integer timeSpentNtt
){
}
