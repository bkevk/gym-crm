package com.bk.core.facade;

import com.bk.core.model.Trainee;
import com.bk.core.model.Trainer;
import com.bk.core.model.Training;
import com.bk.core.service.TraineeService;
import com.bk.core.service.TrainerService;
import com.bk.core.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.info("GymFacadeImpl initialized with services.");
    }

    // Trainee operations
    @Override
    public void createTrainee(Trainee trainee) {
        traineeService.createTrainee(trainee);
        logger.info("Facade: Created Trainee {}", trainee);
    }

    @Override
    public Trainee getTrainee(Long id) {
        Trainee trainee = traineeService.getTrainee(id);
        logger.debug("Facade: Retrieved Trainee {}", trainee);
        return trainee;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        List<Trainee> trainees = traineeService.getAllTrainees();
        logger.debug("Facade: Retrieved all Trainees, count={}", trainees.size());
        return trainees;
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeService.updateTrainee(trainee);
        logger.info("Facade: Updated Trainee {}", trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeService.deleteTrainee(id);
        logger.info("Facade: Deleted Trainee with id {}", id);
    }

    // Trainer operations
    @Override
    public void createTrainer(Trainer trainer) {
        trainerService.createTrainer(trainer);
        logger.info("Facade: Created Trainer {}", trainer);
    }

    @Override
    public Trainer getTrainer(Long id) {
        Trainer trainer = trainerService.getTrainer(id);
        logger.debug("Facade: Retrieved Trainer {}", trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        logger.debug("Facade: Retrieved all Trainers, count={}", trainers.size());
        return trainers;
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerService.updateTrainer(trainer);
        logger.info("Facade: Updated Trainer {}", trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerService.deleteTrainer(id);
        logger.info("Facade: Deleted Trainer with id {}", id);
    }

    // Training operations
    @Override
    public void createTraining(Long id, Training training) {
        trainingService.createTraining(id, training);
        logger.info("Facade: Created Training {}", training);
    }

    @Override
    public Training getTraining(Long id) {
        Training training = trainingService.getTraining(id);
        logger.debug("Facade: Retrieved Training {}", training);
        return training;
    }

    @Override
    public List<Training> getAllTrainings() {
        List<Training> trainings = trainingService.getAllTrainings();
        logger.debug("Facade: Retrieved all Trainings, count={}", trainings.size());
        return trainings;
    }

    @Override
    public void updateTraining(Long id, Training training) {
        trainingService.updateTraining(id, training);
        logger.info("Facade: Updated Training {}", training);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingService.deleteTraining(id);
        logger.info("Facade: Deleted Training with id {}", id);
    }
}
