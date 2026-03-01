package com.bk.gym.service.impl;

import com.bk.gym.entity.Trainee;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.service.TraineeService;
import com.bk.gym.util.PasswordGenerator;
import com.bk.gym.util.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TraineeServiceImpl implements TraineeService {
    private TraineeRepository traineeRepository;

    private UsernameGenerator usernameGenerator;

    @Autowired
    public void setUsernameGenerator(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }

    @Autowired
    public void setTraineeRepositoryForUsernameGenerator(TraineeRepository traineeRepository) {
        if (usernameGenerator != null) {
            usernameGenerator.setTraineeRepository(traineeRepository);
        }
    }
    @Autowired
    public void setTraineeRepository(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public void createTrainee(Trainee trainee) {
        String username = usernameGenerator.generateUniqueUsername(trainee.getFirstName(), trainee.getLastName());
        String password = PasswordGenerator.generateRandomPassword();
        trainee.setUsername(username);
        trainee.setPassword(password);
        traineeRepository.save(trainee);
        log.info("Created Trainee with username: {} and password: {}", username, password);
    }

    @Override
    public Trainee getTrainee(Long id) {
        Trainee trainee = traineeRepository.findById(id).orElse(null);
        log.debug("Retrieved Trainee: {}", trainee);
        return trainee;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        List<Trainee> trainees = traineeRepository.findAll();
        log.debug("Retrieved all Trainees: count={}", trainees.size());
        return trainees;
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
        log.info("Updated Trainee: {}", trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeRepository.deleteById(id);
        log.info("Deleted Trainee with id: {}", id);
    }

    @Override
    public boolean authenticateTrainee(String username, String password) {
        Optional<Trainee> trainee = traineeRepository.findTraineeByUsername(username);
        return trainee.filter(value -> password.equals(value.getUsername())).isPresent();
    }

    @Override
    public Trainee getTraineeByUsername(String userName) {
        return traineeRepository.findTraineeByUsername(userName).orElse(null);
    }

    @Override
    public boolean passwordChange(String username, String oldPassword, String newPassword) {
        if(authenticateTrainee(username, oldPassword)){
            Trainee trainee = traineeRepository.findTraineeByUsername(username).get();
            trainee.setPassword(newPassword);
            traineeRepository.save(trainee);
            return true;
        }
        return false;
    }

    @Override
    public void activateTrainee(Long id) {
        Trainee trainee = traineeRepository.findById(id).orElse(null);
        if(trainee != null) {
            trainee.setActive(true);
            traineeRepository.save(trainee);
        }
    }

    @Override
    public void deactivateTrainee(Long id) {
        Trainee trainee = traineeRepository.findById(id).orElse(null);
        if(trainee != null) {
            trainee.setActive(false);
            traineeRepository.save(trainee);
        }
    }
}
