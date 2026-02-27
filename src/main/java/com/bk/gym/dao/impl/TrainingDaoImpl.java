package com.bk.gym.dao.impl;

import com.bk.gym.dao.TrainingDao;
import com.bk.gym.model.Training;
import com.bk.gym.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TrainingDaoImpl implements TrainingDao {
    private Storage storage;

    @Override
    public void save(Long id, Training training) {
        storage.put(Storage.TRAINING_NAMESPACE, id, training);
        log.info("Saved Training: {}", training);
    }

    @Override
    public Training findById(Long id) {
        Training training = (Training) storage.get(Storage.TRAINING_NAMESPACE, id);
        log.debug("Found Training by id {}: {}", id, training);
        return training;
    }

    @Override
    public List<Training> findAll() {
        List<Training> list = new ArrayList<>();
        for (Object obj : storage.getAll(Storage.TRAINING_NAMESPACE)) {
            list.add((Training) obj);
        }
        log.debug("Found all Trainings: count={}", list.size());
        return list;
    }

    @Override
    public void update(Long id, Training training) {
        storage.put(Storage.TRAINING_NAMESPACE, id, training);
        log.info("Updated Training: {}", training);
    }

    @Override
    public void delete(Long id) {
        storage.remove(Storage.TRAINING_NAMESPACE, id);
        log.info("Deleted Training with id: {}", id);
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
