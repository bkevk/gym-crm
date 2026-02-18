package com.bk.core.dao.impl;

import com.bk.core.model.Trainer;
import com.bk.core.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainerDaoImplTest {

    private Storage storage;
    private TrainerDaoImpl trainerDao;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        trainerDao = new TrainerDaoImpl();
        trainerDao.setStorage(storage);
    }

    @Test
    void testSaveAndFindById() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        trainerDao.save(trainer);
        Trainer found = trainerDao.findById(1L);
        assertEquals(trainer, found);
    }

    @Test
    void testFindAll() {
        Trainer t1 = new Trainer("A", "B", true, "Spec", 1L);
        Trainer t2 = new Trainer("C", "D", true, "Spec", 2L);
        trainerDao.save(t1);
        trainerDao.save(t2);
        List<Trainer> all = trainerDao.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testUpdate() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        trainerDao.save(trainer);
        trainer.setSpecialization("Pilates");
        trainerDao.update(trainer);
        assertEquals("Pilates", trainerDao.findById(1L).getSpecialization());
    }

    @Test
    void testDelete() {
        Trainer trainer = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        trainerDao.save(trainer);
        trainerDao.delete(1L);
        assertNull(trainerDao.findById(1L));
    }
}