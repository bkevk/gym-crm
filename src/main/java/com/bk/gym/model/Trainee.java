package com.bk.gym.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Trainee extends User {
    private LocalDate dateOfBirth;
    private String address;
    private Long userId;

    public Trainee(String firstName, String lastName, boolean isActive, LocalDate dateOfBirth, String address, Long userId) {
        super(firstName, lastName, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
    }
}
