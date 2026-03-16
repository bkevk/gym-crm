package com.bk.gym.service;

import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TrainerRepository;
import com.bk.gym.util.UsernameGenerator;
import com.bk.gym.service.impl.TrainerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    private TrainerRepository trainerRepository;
    private UsernameGenerator usernameGenerator;
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        trainerRepository = mock(TrainerRepository.class);
        usernameGenerator = mock(UsernameGenerator.class);
        trainerService = new TrainerServiceImpl();
        trainerService.setUsernameGenerator(usernameGenerator);
        trainerService.setTrainerRepository(trainerRepository);
    }

    @Test
    void testCreateTrainer_SetsUsernameAndPassword() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        when(usernameGenerator.generateUniqueUsername("Jane", "Doe")).thenReturn("Jane.Doe");

        trainerService.createTrainer(trainer);

        ArgumentCaptor<Trainer> captor = ArgumentCaptor.forClass(Trainer.class);
        verify(trainerRepository).save(captor.capture());
        Trainer saved = captor.getValue();
        assertEquals("Jane.Doe", saved.getUsername());
        assertNotNull(saved.getPassword());
        assertEquals(10, saved.getPassword().length());
    }

    @Test
    void testGetTrainer_ReturnsCorrectTrainer() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));
        Trainer result = trainerService.getTrainer(1L);
        assertEquals(trainer, result);
    }

    @Test
    void testGetAllTrainers_ReturnsList() {
        when(trainerRepository.findAll()).thenReturn(Arrays.asList(
                new Trainer("A", "B", true, "Spec", 1L),
                new Trainer("C", "D", true, "Spec", 2L)
        ));
        assertEquals(2, trainerService.getAllTrainers().size());
    }

    @Test
    void testUpdateTrainer_DelegatesToDao() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        trainerService.updateTrainer(trainer);
        verify(trainerRepository).save(trainer);
    }

    @Test
    void testDeleteTrainer_DelegatesToDao() {
        trainerService.deleteTrainer(1L);
        verify(trainerRepository).deleteById(1L);
    }
}