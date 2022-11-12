package com.example.fiegerunner.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PerformanceDto {

    Integer expertis;
    String firstName;
    String lastName;

    Integer uphOptimus;
    Integer totalQlOptimus;
    String totalTimeOptimus;

    Integer uphSingle;
    Integer totalQlSingle;
    String totalTimeSingle;

    Integer uphMulti;
    Integer totalQlMulti;
    String totalTimeMulti;

    Integer uphSort;
    Integer totalQlSort;
    String totalTimeSort;

    Integer uphWMO;
    Integer totalQlWMO;
    String totalTimeWMO;
}
