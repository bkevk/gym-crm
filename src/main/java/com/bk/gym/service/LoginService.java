package com.bk.gym.service;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public LoginService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public boolean authenticate(String username, String password, String transactionId) {
        log.info("[{}] Authenticating user: {}", transactionId, username);

        Trainee trainee = traineeRepository.findByUsername(username);
        if (trainee != null && trainee.getPassword().equals(password)) {
            log.info("[{}] Trainee login successful: {}", transactionId, username);
            return true;
        }

        Trainer trainer = trainerRepository.findByUsername(username);
        if (trainer != null && trainer.getPassword().equals(password)) {
            log.info("[{}] Trainer login successful: {}", transactionId, username);
            return true;
        }

        log.warn("[{}] Login failed for username: {}", transactionId, username);
        return false;
    }
}
