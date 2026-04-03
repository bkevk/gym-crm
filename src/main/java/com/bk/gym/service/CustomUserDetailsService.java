package com.bk.gym.service;

import com.bk.gym.entity.Trainee;
import com.bk.gym.entity.Trainer;
import com.bk.gym.repository.TraineeRepository;
import com.bk.gym.repository.TrainerRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final TraineeRepository traineeRepo;
    private final TrainerRepository trainerRepo;

    public CustomUserDetailsService(TraineeRepository traineeRepo, TrainerRepository trainerRepo) {
        this.traineeRepo = traineeRepo;
        this.trainerRepo = trainerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Trainee trainee = traineeRepo.findByUsername(username);
        if (trainee != null) {
            return User.withUsername(trainee.getUsername())
                    .password(trainee.getPassword())
                    .roles("TRAINEE")
                    .build();
        }
        Trainer trainer = trainerRepo.findByUsername(username);
        if (trainer != null) {
            return User.withUsername(trainer.getUsername())
                    .password(trainer.getPassword())
                    .roles("TRAINER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
