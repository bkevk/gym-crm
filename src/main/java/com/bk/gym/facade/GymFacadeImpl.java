package com.bk.gym.facade;

import com.bk.gym.model.Trainee;
import com.bk.gym.model.Trainer;
import com.bk.gym.model.Training;
import com.bk.gym.service.TraineeService;
import com.bk.gym.service.TrainerService;
import com.bk.gym.service.TrainingService;
import org.springframework.stereotype.Component;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GymFacadeImpl implements GymFacade {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    // Constructor-based injection for services
    public GymFacadeImpl(TraineeService traineeService,
                         TrainerService trainerService,
                         TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
        log.info("GymFacadeImpl initialized with services.");
    }

    // Trainee operations
    @Override
    public void createTrainee(Trainee trainee) {
        traineeService.createTrainee(trainee);
        log.info("Facade: Created Trainee {}", trainee);
    }

    @Override
    public Trainee getTrainee(Long id) {
        Trainee trainee = traineeService.getTrainee(id);
        log.debug("Facade: Retrieved Trainee {}", trainee);
        return trainee;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        List<Trainee> trainees = traineeService.getAllTrainees();
        log.debug("Facade: Retrieved all Trainees, count={}", trainees.size());
        return trainees;
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeService.updateTrainee(trainee);
        log.info("Facade: Updated Trainee {}", trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeService.deleteTrainee(id);
        log.info("Facade: Deleted Trainee with id {}", id);
    }

    // Trainer operations
    @Override
    public void createTrainer(Trainer trainer) {
        trainerService.createTrainer(trainer);
        log.info("Facade: Created Trainer {}", trainer);
    }

    @Override
    public Trainer getTrainer(Long id) {
        Trainer trainer = trainerService.getTrainer(id);
        log.debug("Facade: Retrieved Trainer {}", trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        log.debug("Facade: Retrieved all Trainers, count={}", trainers.size());
        return trainers;
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerService.updateTrainer(trainer);
        log.info("Facade: Updated Trainer {}", trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerService.deleteTrainer(id);
        log.info("Facade: Deleted Trainer with id {}", id);
    }

    // Training operations
    @Override
    public void createTraining(Long id, Training training) {
        trainingService.createTraining(id, training);
        log.info("Facade: Created Training {}", training);
    }

    @Override
    public Training getTraining(Long id) {
        Training training = trainingService.getTraining(id);
        log.debug("Facade: Retrieved Training {}", training);
        return training;
    }

    @Override
    public List<Training> getAllTrainings() {
        List<Training> trainings = trainingService.getAllTrainings();
        log.debug("Facade: Retrieved all Trainings, count={}", trainings.size());
        return trainings;
    }

    @Override
    public void updateTraining(Long id, Training training) {
        trainingService.updateTraining(id, training);
        log.info("Facade: Updated Training {}", training);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingService.deleteTraining(id);
        log.info("Facade: Deleted Training with id {}", id);
    }
}
