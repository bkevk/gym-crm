package com.bk.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private String firstName;
    private String lastName;
    private String username;
    private boolean isActive;
    private String password;
}
