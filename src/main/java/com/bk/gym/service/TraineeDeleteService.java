package com.bk.gym.service;

import com.bk.gym.entity.Trainee;
import com.bk.gym.repository.TraineeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TraineeDeleteService {
    private final TraineeRepository traineeRepository;

    public TraineeDeleteService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Transactional
    public void deleteTraineeByUsername(String username, String transactionId) {
        log.info("[{}] Deleting trainee profile for username={}", transactionId, username);
        Trainee trainee = traineeRepository.findByUsername(username);
        if (trainee == null) {
            log.warn("[{}] Trainee not found: {}", transactionId, username);
            throw new RuntimeException("Trainee not found");
        }
        traineeRepository.delete(trainee);
        log.info("[{}] Trainee profile deleted for username={}", transactionId, username);
    }
}
