package com.bk.gym.facade;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.entity.Training;

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
    void createTraining(Training training);
    Training getTraining(Long id);
    List<Training> getAllTrainings();
    void updateTraining(Training training);
    void deleteTraining(Long id);
}
