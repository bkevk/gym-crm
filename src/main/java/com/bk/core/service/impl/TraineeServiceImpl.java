package com.bk.core.service.impl;

import com.bk.core.dao.TraineeDao;
import com.bk.core.model.Trainee;
import com.bk.core.service.TraineeService;
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
public class TraineeServiceImpl implements TraineeService {


    private TraineeDao traineeDao;

    private UsernameGenerator usernameGenerator;

    @Autowired
    public void setUsernameGenerator(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }

    @Autowired
    public void setTraineeDaoForUsernameGenerator(TraineeDao traineeDao) {
        if (usernameGenerator != null) {
            usernameGenerator.setTraineeDao(traineeDao);
        }
    }
    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    @Override
    public void createTrainee(Trainee trainee) {
        String username = usernameGenerator.generateUniqueUsername(trainee.getFirstName(), trainee.getLastName());
        String password = PasswordGenerator.generateRandomPassword();
        trainee.setUsername(username);
        trainee.setPassword(password);
        traineeDao.save(trainee);
        logger.info("Created Trainee with username: {} and password: {}", username, password);
    }

    @Override
    public Trainee getTrainee(Long id) {
        Trainee trainee = traineeDao.findById(id);
        logger.debug("Retrieved Trainee: {}", trainee);
        return trainee;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        List<Trainee> trainees = traineeDao.findAll();
        logger.debug("Retrieved all Trainees: count={}", trainees.size());
        return trainees;
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeDao.update(trainee);
        logger.info("Updated Trainee: {}", trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeDao.delete(id);
        logger.info("Deleted Trainee with id: {}", id);
    }
}
