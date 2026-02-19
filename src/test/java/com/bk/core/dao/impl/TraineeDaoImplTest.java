package com.bk.core.dao.impl;

import com.bk.core.model.Trainee;
import com.bk.core.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDaoImplTest {

    private Storage storage;
    private TraineeDaoImpl traineeDao;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        traineeDao = new TraineeDaoImpl();
        traineeDao.setStorage(storage); // Use package-private setter if needed
    }

    @Test
    void testSaveAndFindById() {
        Trainee trainee = new Trainee("Anna", "Brown", true, LocalDate.of(1995, 5, 15), "Address", 1L);
        traineeDao.save(trainee);
        Trainee found = traineeDao.findById(1L);
        assertEquals(trainee, found);
    }

    @Test
    void testFindAll() {
        Trainee t1 = new Trainee("A", "B", true, LocalDate.now(), "Addr1", 1L);
        Trainee t2 = new Trainee("C", "D", true, LocalDate.now(), "Addr2", 2L);
        traineeDao.save(t1);
        traineeDao.save(t2);
        List<Trainee> all = traineeDao.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testUpdate() {
        Trainee trainee = new Trainee("Anna", "Brown", true, LocalDate.of(1995, 5, 15), "Address", 1L);
        traineeDao.save(trainee);
        trainee.setAddress("New Address");
        traineeDao.update(trainee);
        assertEquals("New Address", traineeDao.findById(1L).getAddress());
    }

    @Test
    void testDelete() {
        Trainee trainee = new Trainee("Anna", "Brown", true, LocalDate.of(1995, 5, 15), "Address", 1L);
        traineeDao.save(trainee);
        traineeDao.delete(1L);
        assertNull(traineeDao.findById(1L));
    }
}
