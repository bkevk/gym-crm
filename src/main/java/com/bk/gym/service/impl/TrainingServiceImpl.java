package com.bk.gym.service.impl;

import com.bk.gym.entity.Training;
import com.bk.gym.entity.TrainingType;
import com.bk.gym.repository.TrainingRepository;
import com.bk.gym.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {
    private TrainingRepository trainingRepository;

    @Override
    public void createTraining(Training training) {
        trainingRepository.save(training);
        log.info("Created Training: {}", training);
    }

    @Override
    public Training getTraining(Long id) {
        Training training = trainingRepository.findById(id).orElse(null);
        log.debug("Retrieved Training: {}", training);
        return training;
    }

    @Override
    public List<Training> getAllTrainings() {
        List<Training> trainings = trainingRepository.findAll();
        log.debug("Retrieved all Trainings: count={}", trainings.size());
        return trainings;
    }

    @Override
    public void updateTraining(Training training) {
        trainingRepository.save(training);
        log.info("Updated Training: {}", training);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
        log.info("Deleted Training with id: {}", id);
    }

    public List<Training> getTrainingByTrainerUsernameAndTrainingType(String trainerUsername, TrainingType trainingType){
        return trainingRepository.getTrainingByTrainerUsernameAndTrainingType(trainerUsername, trainingType);
    }

    public List<Training> getTrainingByTraineeUsernameAndDateIsBetween(String traineeUsername, LocalDateTime dateAfter, LocalDateTime dateBefore){
        return trainingRepository.getTrainingByTraineeUsernameAndDateIsBetween(traineeUsername, dateAfter, dateBefore);
    }

    @Autowired
    public void setTrainerDao(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }
}
