package com.bk.gym.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long traineeId;
    private Long trainerId;
    private String name;
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;
    private LocalDateTime date;
    private int duration; // Duration in minutes
    @ManyToOne
    @JoinColumn(name = "trainer_userId")
    private Trainer trainer;
    @ManyToOne
    @JoinColumn(name = "trainee_userId")
    private Trainee trainee;

    public Training(Long traineeId, Long trainerId, String name, TrainingType trainingType, LocalDateTime date, int duration) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.name = name;
        this.trainingType = trainingType;
        this.date = date;
        this.duration = duration;
    }
}
