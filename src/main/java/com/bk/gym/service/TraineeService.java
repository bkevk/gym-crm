package com.bk.gym.service;

import com.bk.gym.dto.TraineeRegistrationRequest;
import com.bk.gym.dto.TraineeRegistrationResponse;
import com.bk.gym.entity.Trainee;
import jakarta.validation.Valid;

import java.util.List;

public interface TraineeService {
    void createTrainee(Trainee trainee);
    Trainee getTrainee(Long id);
    List<Trainee> getAllTrainees();
    void updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);
    boolean authenticateTrainee(String username, String password);
    Trainee getTraineeByUsername(String userName);
    boolean passwordChange(String username, String oldPassword, String newPassword);
    void activateTrainee(Long id);
    void deactivateTrainee(Long id);

    TraineeRegistrationResponse registerTrainee(@Valid TraineeRegistrationRequest request, String transactionId);
}
