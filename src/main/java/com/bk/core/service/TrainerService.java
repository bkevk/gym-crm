package com.bk.core.service;

import com.bk.core.model.Trainer;
import java.util.List;

public interface TrainerService {
    void createTrainer(Trainer trainer);
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
    void updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);
}
