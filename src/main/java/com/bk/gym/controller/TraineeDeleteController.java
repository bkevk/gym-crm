package com.bk.gym.controller;

import com.bk.gym.service.TraineeDeleteService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "Trainee API")
public class TraineeDeleteController {
    private static final Logger logger = LoggerFactory.getLogger(TraineeDeleteController.class);

    private final TraineeDeleteService traineeDeleteService;

    public TraineeDeleteController(TraineeDeleteService traineeDeleteService) {
        this.traineeDeleteService = traineeDeleteService;
    }

    @DeleteMapping("/trainee/profile")
    @ApiOperation("Delete Trainee Profile by Username (cascade deletes trainings)")
    public ResponseEntity<Void> deleteTraineeProfile(
            @ApiParam("Trainee username") @RequestParam String username,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        logger.info("[{}] Delete Trainee Profile: username={}", transactionId, username);
        traineeDeleteService.deleteTraineeByUsername(username, transactionId);
        logger.info("[{}] Trainee profile deleted: username={}", transactionId, username);
        return ResponseEntity.ok().build();
    }
}
