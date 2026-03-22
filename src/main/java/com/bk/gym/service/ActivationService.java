package com.bk.gym.service;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActivationService {
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public ActivationService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public boolean activateOrDeactivateTrainee(String username, boolean isActive, String transactionId) {
        log.info("[{}] Activation/deactivation request for trainee: {} -> {}", transactionId, username, isActive);
        Trainee trainee = traineeRepository.findByUsername(username);
        if (trainee == null) {
            log.warn("[{}] Trainee not found: {}", transactionId, username);
            throw new RuntimeException("Trainee not found");
        }
        if (trainee.isActive() == isActive) {
            log.warn("[{}] Trainee already in requested state: {} -> {}", transactionId, username, isActive);
            throw new RuntimeException("Trainee already in requested state");
        }
        trainee.setActive(isActive);
        traineeRepository.save(trainee);
        log.info("[{}] Trainee activation state changed: {} -> {}", transactionId, username, isActive);
        return true;
    }

    public boolean activateOrDeactivateTrainer(String username, boolean isActive, String transactionId) {
        log.info("[{}] Activation/deactivation request for trainer: {} -> {}", transactionId, username, isActive);
        Trainer trainer = trainerRepository.findByUsername(username);
        if (trainer == null) {
            log.warn("[{}] Trainer not found: {}", transactionId, username);
            throw new RuntimeException("Trainer not found");
        }
        if (trainer.isActive() == isActive) {
            log.warn("[{}] Trainer already in requested state: {} -> {}", transactionId, username, isActive);
            throw new RuntimeException("Trainer already in requested state");
        }
        trainer.setActive(isActive);
        trainerRepository.save(trainer);
        log.info("[{}] Trainer activation state changed: {} -> {}", transactionId, username, isActive);
        return true;
    }
}
