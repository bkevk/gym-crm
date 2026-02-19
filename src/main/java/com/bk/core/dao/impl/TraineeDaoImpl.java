package com.bk.core.dao.impl;

import com.bk.core.dao.TraineeDao;
import com.bk.core.model.Trainee;
import com.bk.core.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TraineeDaoImpl implements TraineeDao {
    private static final Logger logger = LoggerFactory.getLogger(TraineeDaoImpl.class);
    private Storage storage;

    @Override
    public void save(Trainee trainee) {
        storage.put(Storage.TRAINEE_NAMESPACE, trainee.getUserId(), trainee);
        logger.info("Saved Trainee: {}", trainee);
    }

    @Override
    public Trainee findById(Long id) {
        Trainee trainee = (Trainee) storage.get(Storage.TRAINEE_NAMESPACE, id);
        logger.debug("Found Trainee by id {}: {}", id, trainee);
        return trainee;
    }

    @Override
    public List<Trainee> findAll() {
        List<Trainee> list = new ArrayList<>();
        for (Object obj : storage.getAll(Storage.TRAINEE_NAMESPACE)) {
            list.add((Trainee) obj);
        }
        logger.debug("Found all Trainees: count={}", list.size());
        return list;
    }

    @Override
    public void update(Trainee trainee) {
        storage.put(Storage.TRAINEE_NAMESPACE, trainee.getUserId(), trainee);
        logger.info("Updated Trainee: {}", trainee);
    }

    @Override
    public void delete(Long id) {
        storage.remove(Storage.TRAINEE_NAMESPACE, id);
        logger.info("Deleted Trainee with id: {}", id);
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
