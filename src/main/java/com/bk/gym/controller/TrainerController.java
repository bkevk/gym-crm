package com.bk.gym.controller;

import com.bk.gym.dto.TrainerRegistrationRequest;
import com.bk.gym.dto.TrainerRegistrationResponse;
import com.bk.gym.service.TrainerService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "Trainer API")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/trainer/register")
    @ApiOperation("Register a new trainer")
    public TrainerRegistrationResponse registerTrainer(
            @ApiParam("Trainer registration request") @Valid @RequestBody TrainerRegistrationRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        log.info("[{}] Register Trainer: {}", transactionId, request);
        TrainerRegistrationResponse response = trainerService.registerTrainer(request, transactionId);
        log.info("[{}] Registration response: {}", transactionId, response);
        return response;
    }
}
