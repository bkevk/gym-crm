package com.bk.gym.controller;

import com.bk.gym.dto.*;
import com.bk.gym.service.ProfileUpdateService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "Profile Update API")
public class ProfileUpdateController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileUpdateController.class);

    private final ProfileUpdateService profileUpdateService;

    public ProfileUpdateController(ProfileUpdateService profileUpdateService) {
        this.profileUpdateService = profileUpdateService;
    }

    @PutMapping("/trainee/profile")
    @ApiOperation("Update Trainee Profile")
    public TraineeProfileUpdateResponse updateTraineeProfile(
            @ApiParam("Trainee profile update request") @Valid @RequestBody TraineeProfileUpdateRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        logger.info("[{}] Update Trainee Profile: username={}", transactionId, request.getUsername());
        return profileUpdateService.updateTraineeProfile(request, transactionId);
    }

    @PutMapping("/trainer/profile")
    @ApiOperation("Update Trainer Profile")
    public TrainerProfileUpdateResponse updateTrainerProfile(
            @ApiParam("Trainer profile update request") @Valid @RequestBody TrainerProfileUpdateRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        logger.info("[{}] Update Trainer Profile: username={}", transactionId, request.getUsername());
        return profileUpdateService.updateTrainerProfile(request, transactionId);
    }
}
