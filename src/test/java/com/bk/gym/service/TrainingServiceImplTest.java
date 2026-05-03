package com.bk.gym.service;

import com.bk.gym.entity.Training;
import com.bk.gym.entity.TrainingType;
import com.bk.gym.repository.TrainingRepository;
import com.bk.gym.service.impl.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {

    private TrainingRepository trainingRepository;
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        trainingRepository = mock(TrainingRepository.class);
        trainingService = new TrainingServiceImpl();
        trainingService.setTrainerDao(trainingRepository); // Use package-private setter if needed
    }

    @Test
    void testCreateTraining() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingService.createTraining(training, null, null);
        verify(trainingRepository).save(training);
    }

    @Test
    void testGetTraining() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        when(trainingRepository.findById(1L)).thenReturn(Optional.of(training));
        Training result = trainingService.getTraining(1L);
        assertEquals(training, result);
    }

    @Test
    void testGetAllTrainings() {
        when(trainingRepository.findAll()).thenReturn(Arrays.asList(
                new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45),
                new Training(2L, 3L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), 60)
        ));
        assertEquals(2, trainingService.getAllTrainings().size());
    }

    @Test
    void testUpdateTraining() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingService.updateTraining(training);
        verify(trainingRepository).save(training);
    }

    @Test
    void testDeleteTraining() {
        trainingService.deleteTraining(1L);
        verify(trainingRepository).deleteById(1L);
    }
}