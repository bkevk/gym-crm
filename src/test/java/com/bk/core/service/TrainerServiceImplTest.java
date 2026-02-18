package com.bk.core.service;

import com.bk.core.dao.TrainerDao;
import com.bk.core.model.Trainer;
import com.bk.core.util.PasswordGenerator;
import com.bk.core.util.UsernameGenerator;
import com.bk.core.service.impl.TrainerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    private TrainerDao trainerDao;
    private UsernameGenerator usernameGenerator;
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        trainerDao = mock(TrainerDao.class);
        usernameGenerator = mock(UsernameGenerator.class);
        trainerService = new TrainerServiceImpl();
        trainerService.setUsernameGenerator(usernameGenerator);
        trainerService.setTrainerDao(trainerDao);
    }

    @Test
    void testCreateTrainer_SetsUsernameAndPassword() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        when(usernameGenerator.generateUniqueUsername("Jane", "Doe")).thenReturn("Jane.Doe");

        trainerService.createTrainer(trainer);

        ArgumentCaptor<Trainer> captor = ArgumentCaptor.forClass(Trainer.class);
        verify(trainerDao).save(captor.capture());
        Trainer saved = captor.getValue();
        assertEquals("Jane.Doe", saved.getUsername());
        assertNotNull(saved.getPassword());
        assertEquals(10, saved.getPassword().length());
    }

    @Test
    void testGetTrainer_ReturnsCorrectTrainer() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        when(trainerDao.findById(1L)).thenReturn(trainer);
        Trainer result = trainerService.getTrainer(1L);
        assertEquals(trainer, result);
    }

    @Test
    void testGetAllTrainers_ReturnsList() {
        when(trainerDao.findAll()).thenReturn(Arrays.asList(
                new Trainer("A", "B", true, "Spec", 1L),
                new Trainer("C", "D", true, "Spec", 2L)
        ));
        assertEquals(2, trainerService.getAllTrainers().size());
    }

    @Test
    void testUpdateTrainer_DelegatesToDao() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        trainerService.updateTrainer(trainer);
        verify(trainerDao).update(trainer);
    }

    @Test
    void testDeleteTrainer_DelegatesToDao() {
        trainerService.deleteTrainer(1L);
        verify(trainerDao).delete(1L);
    }
}