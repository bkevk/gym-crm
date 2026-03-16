package com.bk.gym.service.impl;

import com.bk.gym.dto.TrainerRegistrationRequest;
import com.bk.gym.dto.TrainerRegistrationResponse;
import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TrainerRepository;
import com.bk.gym.service.TrainerService;
import com.bk.gym.util.PasswordGenerator;
import com.bk.gym.util.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;

    private UsernameGenerator usernameGenerator;

    private PasswordGenerator passwordGenerator;

    @Autowired
    public void setUsernameGenerator(UsernameGenerator usernameGenerator) {
        this.usernameGenerator = usernameGenerator;
    }
    @Autowired
    public void setPasswordGenerator(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }


    @Autowired
    public void setTrainerRepositoryForUsernameGenerator(TrainerRepository trainerRepository) {
        if (usernameGenerator != null) {
            usernameGenerator.setTrainerRepository(trainerRepository);
        }
    }

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public void createTrainer(Trainer trainer) {
        String username = usernameGenerator.generateUniqueUsername(trainer.getFirstName(), trainer.getLastName());
        String password = PasswordGenerator.generateRandomPassword();
        trainer.setUsername(username);
        trainer.setPassword(password);
        trainerRepository.save(trainer);
        log.info("Created Trainer with username: {} and password: {}", username, password);
    }

    @Override
    public Trainer getTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElse(null);
        log.debug("Retrieved Trainer: {}", trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        log.debug("Retrieved all Trainers: count={}", trainers.size());
        return trainers;
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
        log.info("Updated Trainer: {}", trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
        log.info("Deleted Trainer with id: {}", id);
    }

    @Override
    public boolean authenticateTrainer(String username, String password) {
        Optional<Trainer> trainer = trainerRepository.findTrainerByUsername(username);
        return trainer.filter(value -> password.equals(value.getUsername())).isPresent();
    }

    @Override
    public Trainer getTraineeByUsername(String userName) {
        return trainerRepository.findTrainerByUsername(userName).orElse(null);
    }

    @Override
    public boolean passwordChange(String username, String oldPassword, String newPassword) {
        if(authenticateTrainer(username, oldPassword)){
            Trainer trainer = trainerRepository.findTrainerByUsername(username).get();
            trainer.setPassword(newPassword);
            trainerRepository.save(trainer);
            return true;
        }
        return false;
    }

    @Override
    public void activateTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElse(null);
        if(trainer != null) {
            trainer.setActive(true);
            trainerRepository.save(trainer);
        }
    }

    @Override
    public void deactivateTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElse(null);
        if(trainer != null) {
            trainer.setActive(false);
            trainerRepository.save(trainer);
        }
    }

    @Override
    public TrainerRegistrationResponse registerTrainer(TrainerRegistrationRequest request, String transactionId) {
        log.info("[{}] Registering trainer: {}", transactionId, request);

        String username = usernameGenerator.generateUniqueUsername(request.getFirstName(), request.getLastName());
        String password = passwordGenerator.generateRandomPassword();

        Trainer trainer = new Trainer();
        trainer.setFirstName(request.getFirstName());
        trainer.setLastName(request.getLastName());
        trainer.setUsername(username);
        trainer.setPassword(password);
        trainer.setSpecialization(request.getSpecialization());
        trainer.setActive(true);

        trainerRepository.save(trainer);

        log.info("[{}] Registered trainer: username={}, password={}", transactionId, username, password);
        return new TrainerRegistrationResponse(username, password);

    }
}
