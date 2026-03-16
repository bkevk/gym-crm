package com.bk.gym.service;

import com.bk.gym.dto.TrainerRegistrationRequest;
import com.bk.gym.dto.TrainerRegistrationResponse;
import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import jakarta.validation.Valid;

import java.util.List;

public interface TrainerService {
    void createTrainer(Trainer trainer);
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
    void updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);
    boolean authenticateTrainer(String username, String password);
    Trainer getTraineeByUsername(String userName);
    boolean passwordChange(String username, String oldPassword, String newPassword);
    void activateTrainer(Long id);
    void deactivateTrainer(Long id);

    TrainerRegistrationResponse registerTrainer(@Valid TrainerRegistrationRequest request, String transactionId);
}
