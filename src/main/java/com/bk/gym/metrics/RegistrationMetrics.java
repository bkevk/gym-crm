package com.bk.gym.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMetrics {
    private final Counter trainerRegistrationCounter;
    private final Counter traineeRegistrationCounter;

    public RegistrationMetrics(MeterRegistry meterRegistry) {
        this.trainerRegistrationCounter = Counter.builder("gymcrm.trainer.registration.count")
                .description("Number of trainer registrations")
                .register(meterRegistry);
        this.traineeRegistrationCounter = Counter.builder("gymcrm.trainee.registration.count")
                .description("Number of trainee registrations")
                .register(meterRegistry);
    }

    public void incrementTrainerRegistration() {
        trainerRegistrationCounter.increment();
    }

    public void incrementTraineeRegistration() {
        traineeRegistrationCounter.increment();
    }
}
