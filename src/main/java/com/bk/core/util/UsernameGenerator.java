package com.bk.core.util;

import com.bk.core.dao.TraineeDao;
import com.bk.core.dao.TrainerDao;
import com.bk.core.model.Trainee;
import com.bk.core.model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsernameGenerator {

    private TraineeDao traineeDao;
    private TrainerDao trainerDao;

    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public String generateUniqueUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;
        String username = baseUsername;
        int serial = 1;

        boolean exists;
        do {
            exists = false;

            if (traineeDao != null) {
                for (Trainee t : traineeDao.findAll()) {
                    if (t.getUsername() != null && t.getUsername().equalsIgnoreCase(username)) {
                        exists = true;
                        break;
                    }
                }
            }

            if (!exists && trainerDao != null) {
                for (Trainer tr : trainerDao.findAll()) {
                    if (tr.getUsername() != null && tr.getUsername().equalsIgnoreCase(username)) {
                        exists = true;
                        break;
                    }
                }
            }
            if (exists) {
                username = baseUsername + serial;
                serial++;
            }
        } while (exists);

        logger.debug("Generated unique username: {}", username);
        return username;
    }
}
