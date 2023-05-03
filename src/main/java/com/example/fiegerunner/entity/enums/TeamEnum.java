package com.example.fiegerunner.entity.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum TeamEnum {

    A ("A"),
    B ("B"),
    C ("C"),
    D ("D"),
    I ("I"),
    E ("E"),
    F ("F"),
    G ("G"),
    H ("H"),
    J ("J"),
    N ("N"),
    R ("R");

    private final String value;

    TeamEnum (String s) {
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
