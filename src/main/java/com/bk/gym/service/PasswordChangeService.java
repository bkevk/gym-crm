package com.bk.gym.service;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordChangeService {

    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public PasswordChangeService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword, String transactionId) {
        log.info("[{}] Password change attempt for username: {}", transactionId, username);

        Trainee trainee = traineeRepository.findByUsername(username);
        if (trainee != null) {
            if (!trainee.getPassword().equals(oldPassword)) {
                log.warn("[{}] Trainee old password mismatch for username: {}", transactionId, username);
                return false;
            }
            trainee.setPassword(newPassword);
            traineeRepository.save(trainee);
            log.info("[{}] Trainee password changed for username: {}", transactionId, username);
            return true;
        }

        Trainer trainer = trainerRepository.findByUsername(username);
        if (trainer != null) {
            if (!trainer.getPassword().equals(oldPassword)) {
                log.warn("[{}] Trainer old password mismatch for username: {}", transactionId, username);
                return false;
            }
            trainer.setPassword(newPassword);
            trainerRepository.save(trainer);
            log.info("[{}] Trainer password changed for username: {}", transactionId, username);
            return true;
        }

        log.warn("[{}] Password change failed, user not found: {}", transactionId, username);
        return false;
    }
}
