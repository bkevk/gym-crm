package com.bk.core.util;

import com.bk.core.dao.TraineeDao;
import com.bk.core.dao.TrainerDao;
import com.bk.core.model.Trainee;
import com.bk.core.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UsernameGeneratorTest {

    private UsernameGenerator usernameGenerator;
    private TraineeDao traineeDao;
    private TrainerDao trainerDao;

    @BeforeEach
    void setUp() {
        usernameGenerator = new UsernameGenerator();
        traineeDao = Mockito.mock(TraineeDao.class);
        trainerDao = Mockito.mock(TrainerDao.class);
        usernameGenerator.setTraineeDao(traineeDao);
        usernameGenerator.setTrainerDao(trainerDao);
    }

    @Test
    void testGenerateUniqueUsername_NoConflict() {
        Mockito.when(traineeDao.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(trainerDao.findAll()).thenReturn(Collections.emptyList());
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith", username);
    }

    @Test
    void testGenerateUniqueUsername_ConflictWithTrainee() {
        Trainee existingTrainee = new Trainee("John", "Smith", true, null, null, 1L);
        existingTrainee.setUsername("John.Smith");
        Mockito.when(traineeDao.findAll()).thenReturn(Arrays.asList(existingTrainee));
        Mockito.when(trainerDao.findAll()).thenReturn(Collections.emptyList());
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith1", username);
    }

    @Test
    void testGenerateUniqueUsername_ConflictWithTrainer() {
        Trainer existingTrainer = new Trainer("John", "Smith", true, "Yoga", 2L);
        existingTrainer.setUsername("John.Smith");
        Mockito.when(traineeDao.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(trainerDao.findAll()).thenReturn(Arrays.asList(existingTrainer));
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith1", username);
    }

    @Test
    void testGenerateUniqueUsername_MultipleConflicts() {
        Trainee t1 = new Trainee("John", "Smith", true, null, null, 1L);
        t1.setUsername("John.Smith");
        Trainer tr1 = new Trainer("John", "Smith", true, "Yoga", 2L);
        tr1.setUsername("John.Smith1");
        Mockito.when(traineeDao.findAll()).thenReturn(Arrays.asList(t1));
        Mockito.when(trainerDao.findAll()).thenReturn(Arrays.asList(tr1));
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith2", username);
    }
}