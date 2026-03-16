package com.bk.gym.controller;

import com.bk.gym.dto.ActivationRequest;
import com.bk.gym.dto.ActivationResponse;
import com.bk.gym.service.ActivationService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "Activation API")
public class ActivationController {
    private static final Logger logger = LoggerFactory.getLogger(ActivationController.class);

    private final ActivationService activationService;

    public ActivationController(ActivationService activationService) {
        this.activationService = activationService;
    }

    @PatchMapping("/trainee/activate")
    @ApiOperation("Activate/De-activate Trainee")
    public ResponseEntity<ActivationResponse> activateTrainee(
            @ApiParam("Activation request") @Valid @RequestBody ActivationRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        logger.info("[{}] Activate/Deactivate Trainee: username={}, isActive={}", transactionId, request.getUsername(), request.isActive());
        activationService.activateOrDeactivateTrainee(request.getUsername(), request.isActive(), transactionId);
        return ResponseEntity.ok(new ActivationResponse("Trainee activation state changed successfully"));
    }

    @PatchMapping("/trainer/activate")
    @ApiOperation("Activate/De-activate Trainer")
    public ResponseEntity<ActivationResponse> activateTrainer(
            @ApiParam("Activation request") @Valid @RequestBody ActivationRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        logger.info("[{}] Activate/Deactivate Trainer: username={}, isActive={}", transactionId, request.getUsername(), request.isActive());
        activationService.activateOrDeactivateTrainer(request.getUsername(), request.isActive(), transactionId);
        return ResponseEntity.ok(new ActivationResponse("Trainer activation state changed successfully"));
    }
}
