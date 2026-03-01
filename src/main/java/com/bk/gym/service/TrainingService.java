package com.bk.gym.service;

import com.bk.gym.entity.Training;
import java.util.List;

public interface TrainingService {
    void createTraining(Training training);
    Training getTraining(Long id);
    List<Training> getAllTrainings();
    void updateTraining(Training training);
    void deleteTraining(Long id);
}
