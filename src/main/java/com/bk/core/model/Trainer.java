package com.bk.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Trainer extends User {
    private String specialization;
    private Long userId;

    public Trainer(String firstName, String lastName, boolean isActive, String specialization, Long userId) {
        super(firstName, lastName, isActive);
        this.specialization = specialization;
        this.userId = userId;
    }
}
