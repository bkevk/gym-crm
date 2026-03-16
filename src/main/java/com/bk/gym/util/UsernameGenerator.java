package com.bk.gym.util;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import lombok.Setter;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
@Component
public class UsernameGenerator {
    // Setter injection for DAOs
    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;

    public String generateUniqueUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;
        String username = baseUsername;
        int serial = 1;

        boolean exists;
        do {
            exists = false;
            // Check trainees
            if (traineeRepository != null) {
                for (Trainee t : traineeRepository.findAll()) {
                    if (t.getUsername() != null && t.getUsername().equalsIgnoreCase(username)) {
                        exists = true;
                        break;
                    }
                }
            }
            // Check trainers
            if (!exists && trainerRepository != null) {
                for (Trainer tr : trainerRepository.findAll()) {
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
