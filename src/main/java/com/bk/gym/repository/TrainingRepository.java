package com.bk.gym.repository;

import com.bk.gym.entity.Training;
import com.bk.gym.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> getTrainingByTrainerUsernameAndTrainingType(String trainerUsername, TrainingType trainingType);
    List<Training> getTrainingByTraineeUsernameAndDateIsBetween(String traineeUsername, LocalDateTime dateAfter, LocalDateTime dateBefore);
}
