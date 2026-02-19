package com.bk.core.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    private Long traineeId;
    private Long trainerId;
    private String name;
    private TrainingType trainingType;
    private LocalDateTime date;
    private int duration; // Duration in minutes
}
