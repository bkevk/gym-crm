package com.bk.core.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainee extends User {
    private LocalDate dateOfBirth;
    private String address;
    private Long userId;
}
