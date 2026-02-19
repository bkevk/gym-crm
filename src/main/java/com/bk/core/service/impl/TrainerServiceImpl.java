package com.bk.core.service.impl;


import com.bk.core.dao.TrainerDao;
import com.bk.core.model.Trainer;
import com.bk.core.service.TrainerService;
import com.bk.core.util.PasswordGenerator;
import com.bk.core.util.UsernameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerDao trainerDao;

    private UsernameGenerator usernameGenerator;

    @Autowired
    public void setUsernameGenerator(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }

    @Autowired
    public void setTrainerDaoForUsernameGenerator(TrainerDao trainerDao) {
        if (usernameGenerator != null) {
            usernameGenerator.setTrainerDao(trainerDao);
        }
    }

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public void createTrainer(Trainer trainer) {
        String username = usernameGenerator.generateUniqueUsername(trainer.getFirstName(), trainer.getLastName());
        String password = PasswordGenerator.generateRandomPassword();
        trainer.setUsername(username);
        trainer.setPassword(password);
        trainerDao.save(trainer);
        logger.info("Created Trainer with username: {} and password: {}", username, password);
    }

    @Override
    public Trainer getTrainer(Long id) {
        Trainer trainer = trainerDao.findById(id);
        logger.debug("Retrieved Trainer: {}", trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = trainerDao.findAll();
        logger.debug("Retrieved all Trainers: count={}", trainers.size());
        return trainers;
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerDao.update(trainer);
        logger.info("Updated Trainer: {}", trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerDao.delete(id);
        logger.info("Deleted Trainer with id: {}", id);
    }
}
