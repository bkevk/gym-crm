package com.bk.gym.controller;

import com.bk.gym.dto.LoginRequest;
import com.bk.gym.dto.LoginResponse;
import com.bk.gym.service.LoginService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "Authentication API")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    @ApiOperation("Login for trainee or trainer")
    public ResponseEntity<LoginResponse> login(
            @ApiParam("Login request") @Valid LoginRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        log.info("[{}] Login attempt: username={}", transactionId, request.getUsername());

        boolean authenticated = loginService.authenticate(request.getUsername(), request.getPassword(), transactionId);
        if (!authenticated) {
            log.warn("[{}] Login failed for username={}", transactionId, request.getUsername());
            return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password"));
        }
        log.info("[{}] Login successful for username={}", transactionId, request.getUsername());
        return ResponseEntity.ok(new LoginResponse("Login successful"));
    }
}
