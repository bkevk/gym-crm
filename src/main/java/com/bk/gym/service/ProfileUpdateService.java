package com.bk.gym.service;

import com.bk.gym.dto.*;
import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ProfileUpdateService {

    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public ProfileUpdateService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public TraineeProfileUpdateResponse updateTraineeProfile(TraineeProfileUpdateRequest request, String transactionId) {
        log.info("[{}] Updating trainee profile for username={}", transactionId, request.getUsername());
        Trainee trainee = traineeRepository.findByUsername(request.getUsername());
        if (trainee == null) {
            log.warn("[{}] Trainee not found: {}", transactionId, request.getUsername());
            throw new RuntimeException("Trainee not found");
        }
        trainee.setFirstName(request.getFirstName());
        trainee.setLastName(request.getLastName());
        trainee.setDateOfBirth(request.getDateOfBirth());
        trainee.setAddress(request.getAddress());
        trainee.setActive(request.isActive());
        traineeRepository.save(trainee);

        TraineeProfileUpdateResponse response = new TraineeProfileUpdateResponse();
        response.setUsername(trainee.getUsername());
        response.setFirstName(trainee.getFirstName());
        response.setLastName(trainee.getLastName());
        response.setDateOfBirth(trainee.getDateOfBirth());
        response.setAddress(trainee.getAddress());
        response.setActive(trainee.isActive());

        log.info("[{}] Trainee profile updated for username={}", transactionId, request.getUsername());
        return response;
    }

    public TrainerProfileUpdateResponse updateTrainerProfile(TrainerProfileUpdateRequest request, String transactionId) {
        log.info("[{}] Updating trainer profile for username={}", transactionId, request.getUsername());
        Trainer trainer = trainerRepository.findByUsername(request.getUsername());
        if (trainer == null) {
            log.warn("[{}] Trainer not found: {}", transactionId, request.getUsername());
            throw new RuntimeException("Trainer not found");
        }
        trainer.setFirstName(request.getFirstName());
        trainer.setLastName(request.getLastName());
        trainer.setActive(request.isActive());
        trainerRepository.save(trainer);

        TrainerProfileUpdateResponse response = new TrainerProfileUpdateResponse();
        response.setUsername(trainer.getUsername());
        response.setFirstName(trainer.getFirstName());
        response.setLastName(trainer.getLastName());
        response.setSpecialization(trainer.getSpecialization());
        response.setActive(trainer.isActive());

        log.info("[{}] Trainer profile updated for username={}", transactionId, request.getUsername());
        return response;
    }
}
