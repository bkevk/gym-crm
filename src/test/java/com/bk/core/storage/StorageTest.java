package com.bk.core.storage;

import com.bk.core.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
    }

    @Test
    void testConstructorInitializesNamespaces() {
        assertNotNull(storage.getNamespaceStorage(Storage.TRAINEE_NAMESPACE));
        assertNotNull(storage.getNamespaceStorage(Storage.TRAINER_NAMESPACE));
        assertNotNull(storage.getNamespaceStorage(Storage.TRAINING_NAMESPACE));
    }

    @Test
    void testPutGetRemoveTrainee() {
        Trainee trainee = new Trainee("Anna", "Brown", true, LocalDate.of(1995, 5, 15), "Address", 1L);
        storage.put(Storage.TRAINEE_NAMESPACE, 1L, trainee);
        Object retrieved = storage.get(Storage.TRAINEE_NAMESPACE, 1L);
        assertEquals(trainee, retrieved);

        storage.remove(Storage.TRAINEE_NAMESPACE, 1L);
        assertNull(storage.get(Storage.TRAINEE_NAMESPACE, 1L));
    }

    @Test
    void testPutGetAllTrainer() {
        Trainer trainer1 = new Trainer("Jane", "Doe", true, "Yoga", 1L);
        Trainer trainer2 = new Trainer("Mark", "Lee", true, "Strength", 2L);
        storage.put(Storage.TRAINER_NAMESPACE, 1L, trainer1);
        storage.put(Storage.TRAINER_NAMESPACE, 2L, trainer2);

        Collection<Object> all = storage.getAll(Storage.TRAINER_NAMESPACE);
        assertEquals(2, all.size());
        assertTrue(all.contains(trainer1));
        assertTrue(all.contains(trainer2));
    }

    @Test
    void testPutGetTraining() {
        Training training = new Training(1L, 2L, "Yoga Basics", TrainingType.YOGA, LocalDateTime.of(2024, 3, 1, 10, 0), 60);
        storage.put(Storage.TRAINING_NAMESPACE, 1L, training);
        Object retrieved = storage.get(Storage.TRAINING_NAMESPACE, 1L);
        assertEquals(training, retrieved);
    }

    @Test
    void testGetNamespaceStorage() {
        Map<Long, Object> traineeMap = storage.getNamespaceStorage(Storage.TRAINEE_NAMESPACE);
        assertNotNull(traineeMap);
        assertTrue(traineeMap.isEmpty());
    }

    @Test
    void testRemoveNonExistent() {
        storage.remove(Storage.TRAINEE_NAMESPACE, 999L); // Should not throw
        assertNull(storage.get(Storage.TRAINEE_NAMESPACE, 999L));
    }

    @Test
    void testInitLoadsDataFromFile() throws Exception {
        // Set the initFilePath to the test resource
        ReflectionTestUtils.setField(storage, "initFilePath", "test-initial-data.txt");
        storage.init();

        // Check that the data was loaded
        Trainee trainee = (Trainee) storage.get(Storage.TRAINEE_NAMESPACE, 1L);
        assertNotNull(trainee);
        assertEquals("John", trainee.getFirstName());
        assertEquals("Smith", trainee.getLastName());
        assertEquals(LocalDate.of(2000, 1, 1), trainee.getDateOfBirth());

        Trainer trainer = (Trainer) storage.get(Storage.TRAINER_NAMESPACE, 2L);
        assertNotNull(trainer);
        assertEquals("Jane", trainer.getFirstName());
        assertEquals("Doe", trainer.getLastName());
        assertEquals("Yoga", trainer.getSpecialization());

        Training training = (Training) storage.get(Storage.TRAINING_NAMESPACE, 3L);
        assertNotNull(training);
        assertEquals("Yoga Basics", training.getName());
        assertEquals(TrainingType.YOGA, training.getTrainingType());
        assertEquals(LocalDateTime.of(2024, 3, 1, 10, 0), training.getDate());
        assertEquals(60, training.getDuration());
    }

    @Test
    void testInitHandlesMissingFileGracefully() {
        ReflectionTestUtils.setField(storage, "initFilePath", "nonexistent-file.txt");
        // Should not throw, but will log an error
        assertDoesNotThrow(() -> storage.init());
    }

    @Test
    void testInitHandlesMalformedLineGracefully() throws Exception {
        // Create a file with a malformed line
        String fileName = "malformed-initial-data.txt";
        try (java.io.PrintWriter out = new java.io.PrintWriter("src/test/resources/" + fileName)) {
            out.println("trainee,notanid,John,Smith,true,2000-01-01,123 Main St");
        }
        ReflectionTestUtils.setField(storage, "initFilePath", fileName);
        // Should not throw, but will log an error
        assertDoesNotThrow(() -> storage.init());
    }
}
