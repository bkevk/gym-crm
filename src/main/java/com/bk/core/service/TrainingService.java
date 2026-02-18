package com.bk.core.service;

import com.bk.core.model.Training;
import java.util.List;

public interface TrainingService {
    void createTraining(Long id, Training training);
    Training getTraining(Long id);
    List<Training> getAllTrainings();
    void updateTraining(Long id, Training training);
    void deleteTraining(Long id);
}