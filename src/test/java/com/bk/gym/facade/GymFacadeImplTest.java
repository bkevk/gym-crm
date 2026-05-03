package com.bk.gym.facade;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.entity.Training;
import com.bk.gym.service.TraineeService;
import com.bk.gym.service.TrainerService;
import com.bk.gym.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GymFacadeImplTest {

    private TraineeService traineeService;
    private TrainerService trainerService;
    private TrainingService trainingService;
    private GymFacadeImpl gymFacade;

    @BeforeEach
    void setUp() {
        traineeService = mock(TraineeService.class);
        trainerService = mock(TrainerService.class);
        trainingService = mock(TrainingService.class);
        gymFacade = new GymFacadeImpl(traineeService, trainerService, trainingService);
    }

    @Test
    void testCreateTrainee_DelegatesToService() {
        Trainee trainee = mock(Trainee.class);
        gymFacade.createTrainee(trainee);
        verify(traineeService).createTrainee(trainee);
    }

    @Test
    void testGetTrainee_DelegatesToService() {

        gymFacade.getTrainee(1L);
        verify(traineeService).getTrainee(1L);
    }

    @Test
    void testGetAllTrainees_DelegatesToService() {
        gymFacade.getAllTrainees();
        verify(traineeService).getAllTrainees();
    }

    @Test
    void testUpdateTrainee_DelegatesToService() {
        Trainee trainee = mock(Trainee.class);
        gymFacade.updateTrainee(trainee);
        verify(traineeService).updateTrainee(trainee);
    }

    @Test
    void testDeleteTrainee_DelegatesToService() {
        gymFacade.deleteTrainee(1L);
        verify(traineeService).deleteTrainee(1L);
    }

    @Test
    void testCreateTrainer_DelegatesToService() {
        Trainer trainer = mock(Trainer.class);
        gymFacade.createTrainer(trainer);
        verify(trainerService).createTrainer(trainer);
    }

    @Test
    void testGetTrainer_DelegatesToService() {
        gymFacade.getTrainer(1L);
        verify(trainerService).getTrainer(1L);
    }

    @Test
    void testGetAllTrainers_DelegatesToService() {
        gymFacade.getAllTrainers();
        verify(trainerService).getAllTrainers();
    }

    @Test
    void testUpdateTrainer_DelegatesToService() {
        Trainer trainer = mock(Trainer.class);
        gymFacade.updateTrainer(trainer);
        verify(trainerService).updateTrainer(trainer);
    }

    @Test
    void testDeleteTrainer_DelegatesToService() {
        gymFacade.deleteTrainer(1L);
        verify(trainerService).deleteTrainer(1L);
    }

    @Test
    void testCreateTraining_DelegatesToService() {
        Training training = mock(Training.class);
        gymFacade.createTraining(training);
        verify(trainingService).createTraining(training, null, null);
    }

    @Test
    void testGetTraining_DelegatesToService() {
        gymFacade.getTraining(1L);
        verify(trainingService).getTraining(1L);
    }

    @Test
    void testGetAllTrainings_DelegatesToService() {
        gymFacade.getAllTrainings();
        verify(trainingService).getAllTrainings();
    }

    @Test
    void testUpdateTraining_DelegatesToService() {
        Training training = mock(Training.class);
        gymFacade.updateTraining(training);
        verify(trainingService).updateTraining(training);
    }

    @Test
    void testDeleteTraining_DelegatesToService() {
        gymFacade.deleteTraining(1L);
        verify(trainingService).deleteTraining(1L);
    }
}