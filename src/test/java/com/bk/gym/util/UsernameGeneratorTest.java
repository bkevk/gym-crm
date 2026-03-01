package com.bk.gym.util;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UsernameGeneratorTest {

    private UsernameGenerator usernameGenerator;
    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;

    @BeforeEach
    void setUp() {
        usernameGenerator = new UsernameGenerator();
        traineeRepository = Mockito.mock(TraineeRepository.class);
        trainerRepository = Mockito.mock(TrainerRepository.class);
        usernameGenerator.setTraineeRepository(traineeRepository);
        usernameGenerator.setTrainerRepository(trainerRepository);
    }

    @Test
    void testGenerateUniqueUsername_NoConflict() {
        Mockito.when(traineeRepository.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(trainerRepository.findAll()).thenReturn(Collections.emptyList());
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith", username);
    }

    @Test
    void testGenerateUniqueUsername_ConflictWithTrainee() {
        Trainee existingTrainee = new Trainee("John", "Smith", true, null, null, 1L);
        existingTrainee.setUsername("John.Smith");
        Mockito.when(traineeRepository.findAll()).thenReturn(Arrays.asList(existingTrainee));
        Mockito.when(trainerRepository.findAll()).thenReturn(Collections.emptyList());
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith1", username);
    }

    @Test
    void testGenerateUniqueUsername_ConflictWithTrainer() {
        Trainer existingTrainer = new Trainer("John", "Smith", true, "Yoga", 2L);
        existingTrainer.setUsername("John.Smith");
        Mockito.when(traineeRepository.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(trainerRepository.findAll()).thenReturn(Arrays.asList(existingTrainer));
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith1", username);
    }

    @Test
    void testGenerateUniqueUsername_MultipleConflicts() {
        Trainee t1 = new Trainee("John", "Smith", true, null, null, 1L);
        t1.setUsername("John.Smith");
        Trainer tr1 = new Trainer("John", "Smith", true, "Yoga", 2L);
        tr1.setUsername("John.Smith1");
        Mockito.when(traineeRepository.findAll()).thenReturn(Arrays.asList(t1));
        Mockito.when(trainerRepository.findAll()).thenReturn(Arrays.asList(tr1));
        String username = usernameGenerator.generateUniqueUsername("John", "Smith");
        assertEquals("John.Smith2", username);
    }
}