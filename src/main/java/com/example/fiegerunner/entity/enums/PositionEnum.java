package com.example.fiegerunner.entity.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum PositionEnum {

    Magazynier ("Magazynier"),
    Mentor ("Mentor"),
    ProblemSolver ("ProblemSolver"),
    TeamLead ("TeamLead"),
    AreaManager ("AreaManager"),
    FirstLvl ("FirstLvl"),
    SeniorProblemSolver ("SeniorProblemSolver"),
    Wozkowy ("Wozkowy"),
    Supporter ("Supporter"),
    WorkFlow ("WorkFlow");

    private final String value;

    PositionEnum(String s) {
        this.value = s;
    }

    public int getId() {
        return this.ordinal();
    }

    public String getValue() {
        return this.value;
    }

    public static List<Map<String, Object>> asList() {
        return Arrays.stream(values())
                .map(enumElem -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", enumElem.getId());
                    item.put("value", enumElem.getValue());
                    return item;
                })
                .collect(Collectors.toList());
    }

}
