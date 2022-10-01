package com.example.fiegerunner.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    AreaManager,
    TeamLead,
    Storekeeper,
    Admin;

    @Override
    public String getAuthority() {
        return name();
    }
}
