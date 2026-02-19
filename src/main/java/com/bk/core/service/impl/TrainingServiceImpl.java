package com.bk.core.service.impl;

import com.bk.core.dao.TrainerDao;
import com.bk.core.dao.TrainingDao;
import com.bk.core.model.Training;
import com.bk.core.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingDao trainingDao;

    @Override
    public void createTraining(Long id, Training training) {
        trainingDao.save(id, training);
        logger.info("Created Training: {}", training);
    }

    @Override
    public Training getTraining(Long id) {
        Training training = trainingDao.findById(id);
        logger.debug("Retrieved Training: {}", training);
        return training;
    }

    @Override
    public List<Training> getAllTrainings() {
        List<Training> trainings = trainingDao.findAll();
        logger.debug("Retrieved all Trainings: count={}", trainings.size());
        return trainings;
    }

    @Override
    public void updateTraining(Long id, Training training) {
        trainingDao.update(id, training);
        logger.info("Updated Training: {}", training);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingDao.delete(id);
        logger.info("Deleted Training with id: {}", id);
    }
    @Autowired
    public void setTrainerDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }
}
