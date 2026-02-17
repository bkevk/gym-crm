package com.bk.core.storage;

import com.bk.core.model.*;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Storage {

    private static final Logger logger = LoggerFactory.getLogger(Storage.class);

    public static final String TRAINEE_NAMESPACE = "trainee";
    public static final String TRAINER_NAMESPACE = "trainer";
    public static final String TRAINING_NAMESPACE = "training";

    private final Map<String, Map<Long, Object>> storage = new ConcurrentHashMap<>();

    @Value("${storage.init.file}")
    private String initFilePath;

    public Storage() {
        storage.put(TRAINEE_NAMESPACE, new ConcurrentHashMap<>());
        storage.put(TRAINER_NAMESPACE, new ConcurrentHashMap<>());
        storage.put(TRAINING_NAMESPACE, new ConcurrentHashMap<>());
    }

    @PostConstruct
    public void init() {
        logger.info("Initializing storage from file: {}", initFilePath);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(initFilePath))
                ))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue; // skip empty lines and comments
                }
                String[] tokens = line.split(",");
                try {
                    switch (tokens[0].toLowerCase()) {
                        case "trainee":
                            // trainee,1,John,Smith,true,2000-01-01,123 Main St
                            Long traineeId = Long.parseLong(tokens[1]);
                            Trainee trainee = new Trainee(
                                    tokens[2], // firstName
                                    tokens[3], // lastName
                                    Boolean.parseBoolean(tokens[4]), // isActive
                                    LocalDate.parse(tokens[5]), // dateOfBirth
                                    tokens[6], // address
                                    traineeId // userId
                            );
                            storage.get(TRAINEE_NAMESPACE).put(traineeId, trainee);
                            logger.debug("Loaded Trainee: {}", trainee);
                            break;
                        case "trainer":
                            // trainer,1,Jane,Doe,true,Yoga
                            Long trainerId = Long.parseLong(tokens[1]);
                            Trainer trainer = new Trainer(
                                    tokens[2], // firstName
                                    tokens[3], // lastName
                                    Boolean.parseBoolean(tokens[4]), // isActive
                                    tokens[5], // specialization
                                    trainerId // userId
                            );
                            storage.get(TRAINER_NAMESPACE).put(trainerId, trainer);
                            logger.debug("Loaded Trainer: {}", trainer);
                            break;
                        case "training":
                            // training,1,1,1,Yoga Basics,YOGA,2024-03-01T10:00,60
                            Long trainingId = Long.parseLong(tokens[1]);
                            Long tTraineeId = Long.parseLong(tokens[2]);
                            Long tTrainerId = Long.parseLong(tokens[3]);
                            String name = tokens[4];
                            TrainingType type = TrainingType.valueOf(tokens[5]);
                            LocalDateTime date = LocalDateTime.parse(tokens[6]);
                            int duration = Integer.parseInt(tokens[7]);
                            Training training = new Training(
                                    tTraineeId,
                                    tTrainerId,
                                    name,
                                    type,
                                    date,
                                    duration
                            );
                            storage.get(TRAINING_NAMESPACE).put(trainingId, training);
                            logger.debug("Loaded Training: {}", training);
                            break;
                        default:
                            logger.warn("Unknown entity type '{}' at line {}: {}", tokens[0], lineNumber, line);
                    }
                } catch (Exception parseEx) {
                    logger.error("Failed to parse line {}: {}. Error: {}", lineNumber, line, parseEx.getMessage());
                }
            }
            logger.info("Storage initialized successfully from file: {}", initFilePath);
        } catch (Exception e) {
            logger.error("Failed to initialize storage from file '{}': {}", initFilePath, e.getMessage(), e);
        }
    }

    public Map<Long, Object> getNamespaceStorage(String namespace) {
        return storage.get(namespace);
    }

    public void put(String namespace, Long id, Object obj) {
        storage.get(namespace).put(id, obj);
        logger.debug("Put object in namespace '{}': id={}, obj={}", namespace, id, obj);
    }

    public Object get(String namespace, Long id) {
        Object obj = storage.get(namespace).get(id);
        logger.debug("Get object from namespace '{}': id={}, obj={}", namespace, id, obj);
        return obj;
    }

    public Collection<Object> getAll(String namespace) {
        Collection<Object> all = storage.get(namespace).values();
        logger.debug("Get all objects from namespace '{}': count={}", namespace, all.size());
        return all;
    }

    public void remove(String namespace, Long id) {
        storage.get(namespace).remove(id);
        logger.debug("Removed object from namespace '{}': id={}", namespace, id);
    }
}