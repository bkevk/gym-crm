package com.bk.gym.service;

import com.bk.gym.entity.Trainee;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.util.UsernameGenerator;
import com.bk.gym.service.impl.TraineeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeServiceImplTest {

    private TraineeRepository traineeRepository;
    private UsernameGenerator usernameGenerator;
    private TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp() {
        traineeRepository = mock(TraineeRepository.class);
        usernameGenerator = mock(UsernameGenerator.class);
        traineeService = new TraineeServiceImpl();
        traineeService.setUsernameGenerator(usernameGenerator);
        traineeService.setTraineeRepository(traineeRepository);
    }

    @Test
    void testCreateTrainee_SetsUsernameAndPassword() {
        Trainee trainee = new Trainee("Anna", "Brown", true, null, null, 2L);
        when(usernameGenerator.generateUniqueUsername("Anna", "Brown")).thenReturn("Anna.Brown");

        traineeService.createTrainee(trainee);

        ArgumentCaptor<Trainee> captor = ArgumentCaptor.forClass(Trainee.class);
        verify(traineeRepository).save(captor.capture());
        Trainee saved = captor.getValue();
        assertEquals("Anna.Brown", saved.getUsername());
        assertNotNull(saved.getPassword());
        assertEquals(10, saved.getPassword().length());
    }

    @Test
    void testGetTrainee_ReturnsCorrectTrainee() {
        Trainee trainee = new Trainee("Anna", "Brown", true, null, null, 2L);
        when(traineeRepository.findById(2L)).thenReturn(Optional.of(trainee));
        Trainee result = traineeService.getTrainee(2L);
        assertEquals(trainee, result);
    }

    @Test
    void testGetAllTrainees_ReturnsList() {
        when(traineeRepository.findAll()).thenReturn(Arrays.asList(
                new Trainee("A", "B", true, null, null, 1L),
                new Trainee("C", "D", true, null, null, 2L)
        ));
        assertEquals(2, traineeService.getAllTrainees().size());
    }

    @Test
    void testUpdateTrainee_DelegatesToDao() {
        Trainee trainee = new Trainee("Anna", "Brown", true, null, null, 2L);
        traineeService.updateTrainee(trainee);
        verify(traineeRepository).save(trainee);
    }

    @Test
    void testDeleteTrainee_DelegatesToDao() {
        traineeService.deleteTrainee(2L);
        verify(traineeRepository).deleteById(2L);
    }
}