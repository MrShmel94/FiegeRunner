package com.example.fiegerunner.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    AreaManager ("Area Manager"),
    TeamLead ("TeamLeader"),
    Storekeeper ("Magazynier"),
    Admin ("Admin");

    Role(String s) {

    }

    @Override
    public String getAuthority() {
        return name();
    }
}
