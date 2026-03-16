package com.bk.gym.service;

import com.bk.gym.dto.TraineeProfileResponse;
import com.bk.gym.dto.TrainerProfileResponse;
import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileService {

    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public ProfileService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public TraineeProfileResponse getTraineeProfile(String username, String transactionId) {
        log.info("[{}] Retrieving trainee profile for username={}", transactionId, username);
        Trainee trainee = traineeRepository.findByUsername(username);
        if (trainee == null) {
            log.warn("[{}] Trainee not found: {}", transactionId, username);
            throw new RuntimeException("Trainee not found");
        }
        TraineeProfileResponse response = new TraineeProfileResponse();
        response.setFirstName(trainee.getFirstName());
        response.setLastName(trainee.getLastName());
        response.setDateOfBirth(trainee.getDateOfBirth());
        response.setAddress(trainee.getAddress());
        response.setActive(trainee.isActive());
        log.info("[{}] Trainee profile retrieved for username={}", transactionId, username);
        return response;
    }

    public TrainerProfileResponse getTrainerProfile(String username, String transactionId) {
        log.info("[{}] Retrieving trainer profile for username={}", transactionId, username);
        Trainer trainer = trainerRepository.findByUsername(username);
        if (trainer == null) {
            log.warn("[{}] Trainer not found: {}", transactionId, username);
            throw new RuntimeException("Trainer not found");
        }
        TrainerProfileResponse response = new TrainerProfileResponse();
        response.setFirstName(trainer.getFirstName());
        response.setLastName(trainer.getLastName());
        response.setSpecialization(trainer.getSpecialization());
        response.setActive(trainer.isActive());
        log.info("[{}] Trainer profile retrieved for username={}", transactionId, username);
        return response;
    }
}
