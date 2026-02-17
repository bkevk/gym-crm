package com.bk.core.model;

import java.time.LocalDateTime;

public class Training {
    private Long traineeId;
    private Long trainerId;
    private String name;
    private TrainingType trainingType;
    private LocalDateTime date;
    private int duration; // Duration in minutes

    public Training() {}

    public Training(Long traineeId, Long trainerId, String name, TrainingType trainingType, LocalDateTime date, int duration) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.name = name;
        this.trainingType = trainingType;
        this.date = date;
        this.duration = duration;
    }

    public Long getTraineeId() { return traineeId; }
    public void setTraineeId(Long traineeId) { this.traineeId = traineeId; }

    public Long getTrainerId() { return trainerId; }
    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TrainingType getTrainingType() { return trainingType; }
    public void setTrainingType(TrainingType trainingType) { this.trainingType = trainingType; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "Training{" +
                "traineeId=" + traineeId +
                ", trainerId=" + trainerId +
                ", name='" + name + '\'' +
                ", trainingType=" + trainingType +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
