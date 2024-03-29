package com.example.fiegerunner.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority {

    AreaManager ("AreaManager"),
    TeamLead ("TeamLeader"),
    Storekeeper ("Magazynier"),
    Admin ("Admin");

    private final String value;

    Role (String s) {
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

    @Override
    public String getAuthority() {
        return name();
    }
}
