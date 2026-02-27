package com.bk.gym.dao.impl;

import com.bk.gym.dao.TrainerDao;
import com.bk.gym.model.Trainer;
import com.bk.gym.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TrainerDaoImpl implements TrainerDao {
    private Storage storage;

    @Override
    public void save(Trainer trainer) {
        storage.put(Storage.TRAINER_NAMESPACE, trainer.getUserId(), trainer);
        log.info("Saved Trainer: {}", trainer);
    }

    @Override
    public Trainer findById(Long id) {
        Trainer trainer = (Trainer) storage.get(Storage.TRAINER_NAMESPACE, id);
        log.debug("Found Trainer by id {}: {}", id, trainer);
        return trainer;
    }

    @Override
    public List<Trainer> findAll() {
        List<Trainer> list = new ArrayList<>();
        for (Object obj : storage.getAll(Storage.TRAINER_NAMESPACE)) {
            list.add((Trainer) obj);
        }
        log.debug("Found all Trainers: count={}", list.size());
        return list;
    }

    @Override
    public void update(Trainer trainer) {
        storage.put(Storage.TRAINER_NAMESPACE, trainer.getUserId(), trainer);
        log.info("Updated Trainer: {}", trainer);
    }

    @Override
    public void delete(Long id) {
        storage.remove(Storage.TRAINER_NAMESPACE, id);
        log.info("Deleted Trainer with id: {}", id);
    }

    @Autowired
    public void setStorage(Storage storage){
        this.storage = storage;
    }
}
