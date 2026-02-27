package com.bk.gym.util;

import com.bk.gym.dao.TraineeDao;
import com.bk.gym.dao.TrainerDao;
import com.bk.gym.model.Trainee;
import com.bk.gym.model.Trainer;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsernameGenerator {
    private TraineeDao traineeDao;
    private TrainerDao trainerDao;

    // Setter injection for DAOs
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
            // Check trainees
            if (traineeDao != null) {
                for (Trainee t : traineeDao.findAll()) {
                    if (t.getUsername() != null && t.getUsername().equalsIgnoreCase(username)) {
                        exists = true;
                        break;
                    }
                }
            }
            // Check trainers
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

        log.debug("Generated unique username: {}", username);
        return username;
    }
}
