package com.example.nutribalance.dto;

public enum Role {
    USER("USER"), COACH("COACH"), ADMIN("ADMIN");

    private String role;

    public String getRole() {
        return role;
    }

    Role(final String role) {
        this.role = role;
    }
}
