package com.bk.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class User {
    private String firstName;
    private String lastName;
    private String username;
    private boolean isActive;
    private String password;

    public User(String firstName, String lastName, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.username = null; // Will be set by service logic
        this.password = null; // Will be set by service logic
    }
}
