package com.bk.gym.metrics;

import com.bk.gym.repository.TraineeRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class TraineeMetrics {
    private final TraineeRepository traineeRepository;
    private final MeterRegistry meterRegistry;

    public TraineeMetrics(TraineeRepository traineeRepository, MeterRegistry meterRegistry) {
        this.traineeRepository = traineeRepository;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        meterRegistry.gauge("gymcrm.trainees.count", traineeRepository, TraineeRepository::count);
    }
}
