package com.bk.gym.service;

import com.bk.gym.model.Trainer;
import java.util.List;

public interface TrainerService {
    void createTrainer(Trainer trainer);
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
    void updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);
}
