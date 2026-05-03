package com.bk.gym.service.impl;

import com.bk.gym.dto.WorkloadUpdateRequest;
import com.bk.gym.entity.Training;
import com.bk.gym.entity.TrainingType;
import com.bk.gym.feign.TrainerWorkloadFeignClient;
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

    @Autowired
    private TrainerWorkloadFeignClient workloadFeignClient;

    @Override
    public void createTraining(Training training, String jwt, String transactionId) {
        trainingRepository.save(training);
        log.info("Created Training: {}", training);
        WorkloadUpdateRequest request = mapToWorkloadRequest(training, "ADD");
        workloadFeignClient.updateWorkload(request, "Bearer " + jwt, transactionId);
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

    private WorkloadUpdateRequest mapToWorkloadRequest(Training training, String actionType) {
        WorkloadUpdateRequest req = new WorkloadUpdateRequest();
        req.setTrainerUsername(training.getTrainer().getUsername());
        req.setFirstName(training.getTrainer().getFirstName());
        req.setLastName(training.getTrainer().getLastName());
        req.setActive(training.getTrainer().isActive());
        req.setTrainingDate(training.getDate().toLocalDate());
        req.setTrainingDuration(training.getDuration());
        req.setActionType(actionType);
        return req;
    }
}
