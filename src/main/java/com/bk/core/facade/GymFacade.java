package com.bk.core.facade;

import com.bk.core.model.Trainee;
import com.bk.core.model.Trainer;
import com.bk.core.model.Training;

import java.util.List;

public interface GymFacade {
    // Trainee operations
    void createTrainee(Trainee trainee);
    Trainee getTrainee(Long id);
    List<Trainee> getAllTrainees();
    void updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);

    // Trainer operations
    void createTrainer(Trainer trainer);
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
    void updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);

    // Training operations
    void createTraining(Long id, Training training);
    Training getTraining(Long id);
    List<Training> getAllTrainings();
    void updateTraining(Long id, Training training);
    void deleteTraining(Long id);
}
