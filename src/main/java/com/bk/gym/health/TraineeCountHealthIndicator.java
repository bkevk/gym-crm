package com.bk.gym.health;

import com.bk.gym.repository.TraineeRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TraineeCountHealthIndicator implements HealthIndicator {
    private final TraineeRepository traineeRepository;

    public TraineeCountHealthIndicator(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public Health health() {
        long count = traineeRepository.count();
        if (count > 0) {
            return Health.up().withDetail("traineeCount", count).build();
        } else {
            return Health.down().withDetail("traineeCount", count).withDetail("reason", "No trainees registered").build();
        }
    }
}
