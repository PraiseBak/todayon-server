package com.kkpae.todayon.domain;

public enum Role {

    USER_ROLE("ROLE_USER"),
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
