package com.bk.core.service;

import com.bk.core.model.Trainee;
import java.util.List;

public interface TraineeService {
    void createTrainee(Trainee trainee);
    Trainee getTrainee(Long id);
    List<Trainee> getAllTrainees();
    void updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);
}