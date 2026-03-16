package com.bk.gym.controller;

import com.bk.gym.dto.PasswordChangeRequest;
import com.bk.gym.dto.PasswordChangeResponse;
import com.bk.gym.service.PasswordChangeService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "Authentication API")
public class PasswordController {
    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);

    private final PasswordChangeService passwordChangeService;

    public PasswordController(PasswordChangeService passwordChangeService) {
        this.passwordChangeService = passwordChangeService;
    }

    @PutMapping("/change-login")
    @ApiOperation("Change password for trainee or trainer")
    public ResponseEntity<PasswordChangeResponse> changePassword(
            @ApiParam("Password change request") @Valid @RequestBody PasswordChangeRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        logger.info("[{}] Password change request: username={}", transactionId, request.getUsername());

        boolean changed = passwordChangeService.changePassword(
                request.getUsername(),
                request.getOldPassword(),
                request.getNewPassword(),
                transactionId
        );
        if (!changed) {
            logger.warn("[{}] Password change failed for username={}", transactionId, request.getUsername());
            return ResponseEntity.status(401).body(new PasswordChangeResponse("Invalid username or old password"));
        }
        logger.info("[{}] Password change successful for username={}", transactionId, request.getUsername());
        return ResponseEntity.ok(new PasswordChangeResponse("Password changed successfully"));
    }
}
