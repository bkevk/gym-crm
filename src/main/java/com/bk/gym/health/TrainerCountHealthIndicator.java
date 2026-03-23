package com.bk.gym.health;

import com.bk.gym.repository.TrainerRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TrainerCountHealthIndicator implements HealthIndicator {
    private final TrainerRepository trainerRepository;

    public TrainerCountHealthIndicator(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Health health() {
        long count = trainerRepository.count();
        if (count > 0) {
            return Health.up().withDetail("trainerCount", count).build();
        } else {
            return Health.down().withDetail("trainerCount", count).withDetail("reason", "No trainers registered").build();
        }
    }
}
