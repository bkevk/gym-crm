package com.bk.gym.controller;

import com.bk.gym.dto.TraineeRegistrationRequest;
import com.bk.gym.dto.TraineeRegistrationResponse;
import com.bk.gym.service.TraineeService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "Trainee API")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping("/trainee/register")
    @ApiOperation("Register a new trainee")
    public TraineeRegistrationResponse registerTrainee(
            @ApiParam("Trainee registration request") @Valid @RequestBody TraineeRegistrationRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        log.info("[{}] Register Trainee: {}", transactionId, request);
        TraineeRegistrationResponse response = traineeService.registerTrainee(request, transactionId);
        log.info("[{}] Registration response: {}", transactionId, response);
        return response;
    }
}
