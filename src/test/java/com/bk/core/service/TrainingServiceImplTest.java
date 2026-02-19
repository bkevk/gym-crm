package com.bk.core.service;

import com.bk.core.dao.TrainingDao;
import com.bk.core.model.Training;
import com.bk.core.model.TrainingType;
import com.bk.core.service.impl.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {

    private TrainingDao trainingDao;
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        trainingDao = mock(TrainingDao.class);
        trainingService = new TrainingServiceImpl();
        trainingService.setTrainerDao(trainingDao); // Use package-private setter if needed
    }

    @Test
    void testCreateTraining() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingService.createTraining(1L, training);
        verify(trainingDao).save(1L, training);
    }

    @Test
    void testGetTraining() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        when(trainingDao.findById(1L)).thenReturn(training);
        Training result = trainingService.getTraining(1L);
        assertEquals(training, result);
    }

    @Test
    void testGetAllTrainings() {
        when(trainingDao.findAll()).thenReturn(Arrays.asList(
                new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45),
                new Training(2L, 3L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), 60)
        ));
        assertEquals(2, trainingService.getAllTrainings().size());
    }

    @Test
    void testUpdateTraining() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingService.updateTraining(1L, training);
        verify(trainingDao).update(1L, training);
    }

    @Test
    void testDeleteTraining() {
        trainingService.deleteTraining(1L);
        verify(trainingDao).delete(1L);
    }
}
