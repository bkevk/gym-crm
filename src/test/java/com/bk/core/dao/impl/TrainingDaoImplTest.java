package com.bk.core.dao.impl;

import com.bk.core.model.Training;
import com.bk.core.model.TrainingType;
import com.bk.core.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainingDaoImplTest {

    private Storage storage;
    private TrainingDaoImpl trainingDao;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        trainingDao = new TrainingDaoImpl();
        trainingDao.setStorage(storage); // Use package-private setter if needed
    }

    @Test
    void testSaveAndFindById() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingDao.save(1L, training);
        Training found = trainingDao.findById(1L);
        assertEquals(training, found);
    }

    @Test
    void testFindAll() {
        Training t1 = new Training(
                1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        Training t2 = new Training(2L, 3L, "Yoga", TrainingType.YOGA, LocalDateTime.now(), 60);
        trainingDao.save(1L, t1);
        trainingDao.save(2L, t2);
        List<Training> all = trainingDao.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testUpdate() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingDao.save(1L, training);
        training.setName("Updated Cardio");
        trainingDao.update(1L, training);
        assertEquals("Updated Cardio", trainingDao.findById(1L).getName());
    }

    @Test
    void testDelete() {
        Training training = new Training(1L, 2L, "Cardio", TrainingType.CARDIO, LocalDateTime.now(), 45);
        trainingDao.save(1L, training);
        trainingDao.delete(1L);
        assertNull(trainingDao.findById(1L));
    }
}