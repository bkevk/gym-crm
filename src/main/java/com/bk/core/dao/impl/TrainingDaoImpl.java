package com.bk.core.dao.impl;

import com.bk.core.dao.TrainingDao;
import com.bk.core.model.Training;
import com.bk.core.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainingDaoImpl implements TrainingDao {
    private static final Logger logger = LoggerFactory.getLogger(TrainingDaoImpl.class);
    private Storage storage;

    @Override
    public void save(Long id, Training training) {
        storage.put(Storage.TRAINING_NAMESPACE, id, training);
        logger.info("Saved Training: {}", training);
    }

    @Override
    public Training findById(Long id) {
        Training training = (Training) storage.get(Storage.TRAINING_NAMESPACE, id);
        logger.debug("Found Training by id {}: {}", id, training);
        return training;
    }

    @Override
    public List<Training> findAll() {
        List<Training> list = new ArrayList<>();
        for (Object obj : storage.getAll(Storage.TRAINING_NAMESPACE)) {
            list.add((Training) obj);
        }
        logger.debug("Found all Trainings: count={}", list.size());
        return list;
    }

    @Override
    public void update(Long id, Training training) {
        storage.put(Storage.TRAINING_NAMESPACE, id, training);
        logger.info("Updated Training: {}", training);
    }

    @Override
    public void delete(Long id) {
        storage.remove(Storage.TRAINING_NAMESPACE, id);
        logger.info("Deleted Training with id: {}", id);
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}