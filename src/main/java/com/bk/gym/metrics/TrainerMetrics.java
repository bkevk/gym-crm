package com.bk.gym.metrics;

import com.bk.gym.repository.TrainerRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class TrainerMetrics {
    private final TrainerRepository trainerRepository;
    private final MeterRegistry meterRegistry;

    public TrainerMetrics(TrainerRepository trainerRepository, MeterRegistry meterRegistry) {
        this.trainerRepository = trainerRepository;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        meterRegistry.gauge("gymcrm.trainers.count", trainerRepository, TrainerRepository::count);
    }
}
