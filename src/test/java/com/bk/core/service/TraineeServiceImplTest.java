package com.bk.core.service;

import com.bk.core.dao.TraineeDao;
import com.bk.core.model.Trainee;
import com.bk.core.util.PasswordGenerator;
import com.bk.core.util.UsernameGenerator;
import com.bk.core.service.impl.TraineeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeServiceImplTest {

    private TraineeDao traineeDao;
    private UsernameGenerator usernameGenerator;
    private TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp() {
        traineeDao = mock(TraineeDao.class);
        usernameGenerator = mock(UsernameGenerator.class);
        traineeService = new TraineeServiceImpl();
        traineeService.setUsernameGenerator(usernameGenerator);
        traineeService.setTraineeDao(traineeDao);
    }

    @Test
    void testCreateTrainee_SetsUsernameAndPassword() {
        Trainee trainee = new Trainee("Anna", "Brown", true, null, null, 2L);
        when(usernameGenerator.generateUniqueUsername("Anna", "Brown")).thenReturn("Anna.Brown");

        traineeService.createTrainee(trainee);

        ArgumentCaptor<Trainee> captor = ArgumentCaptor.forClass(Trainee.class);
        verify(traineeDao).save(captor.capture());
        Trainee saved = captor.getValue();
        assertEquals("Anna.Brown", saved.getUsername());
        assertNotNull(saved.getPassword());
        assertEquals(10, saved.getPassword().length());
    }

    @Test
    void testGetTrainee_ReturnsCorrectTrainee() {
        Trainee trainee = new Trainee("Anna", "Brown", true, null, null, 2L);
        when(traineeDao.findById(2L)).thenReturn(trainee);
        Trainee result = traineeService.getTrainee(2L);
        assertEquals(trainee, result);
    }

    @Test
    void testGetAllTrainees_ReturnsList() {
        when(traineeDao.findAll()).thenReturn(Arrays.asList(
                new Trainee("A", "B", true, null, null, 1L),
                new Trainee("C", "D", true, null, null, 2L)
        ));
        assertEquals(2, traineeService.getAllTrainees().size());
    }

    @Test
    void testUpdateTrainee_DelegatesToDao() {
        Trainee trainee = new Trainee("Anna", "Brown", true, null, null, 2L);
        traineeService.updateTrainee(trainee);
        verify(traineeDao).update(trainee);
    }

    @Test
    void testDeleteTrainee_DelegatesToDao() {
        traineeService.deleteTrainee(2L);
        verify(traineeDao).delete(2L);
    }
}
