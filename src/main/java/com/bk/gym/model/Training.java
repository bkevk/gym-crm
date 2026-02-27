package com.bk.gym.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Training {
    private Long traineeId;
    private Long trainerId;
    private String name;
    private TrainingType trainingType;
    private LocalDateTime date;
    private int duration; // Duration in minutes

    public Training(Long traineeId, Long trainerId, String name, TrainingType trainingType, LocalDateTime date, int duration) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.name = name;
        this.trainingType = trainingType;
        this.date = date;
        this.duration = duration;
    }
}
