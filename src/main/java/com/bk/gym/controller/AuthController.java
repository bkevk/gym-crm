package com.bk.gym.controller;

import com.bk.gym.dto.LoginRequest;
import com.bk.gym.dto.LoginResponse;
import com.bk.gym.security.BruteForceProtector;
import com.bk.gym.security.JwtUtil;
import com.bk.gym.service.CustomUserDetailsService;
import com.bk.gym.util.TransactionIdGenerator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "Authentication API")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final BruteForceProtector bruteForceProtector;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            BruteForceProtector bruteForceProtector,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.bruteForceProtector = bruteForceProtector;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    @ApiOperation("Login for trainee or trainer")
    public ResponseEntity<LoginResponse> login(
            @ApiParam("Login request") @Valid LoginRequest request,
            @RequestHeader(value = "Transaction-Id", required = false) String transactionId) {
        transactionId = TransactionIdGenerator.ensureTransactionId(transactionId);
        String username = request.getUsername();
        log.info("[{}] Login attempt: username={}", transactionId, username);

        if (bruteForceProtector.isBlocked(username)) {
            log.warn("[{}] User {} is temporarily blocked due to too many failed login attempts", transactionId, username);
            return ResponseEntity.status(429).body(new LoginResponse("User is temporarily blocked due to too many failed login attempts. Try again later."));
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                bruteForceProtector.loginFailed(username);
                log.warn("[{}] Login failed for username={} (wrong password)", transactionId, username);
                return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password"));
            }

            bruteForceProtector.loginSucceeded(username);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, request.getPassword())
            );

            String token = jwtUtil.generateToken(username);
            log.info("[{}] Login successful for username={}", transactionId, username);
            return ResponseEntity.ok(new LoginResponse("Login successful", token));
        } catch (UsernameNotFoundException e) {
            bruteForceProtector.loginFailed(username);
            log.warn("[{}] Login failed for username={} (user not found)", transactionId, username);
            return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password"));
        } catch (LockedException e) {
            log.warn("[{}] Login failed for username={} (account locked)", transactionId, username);
            return ResponseEntity.status(423).body(new LoginResponse("Account is locked"));
        } catch (Exception e) {
            log.error("[{}] Login error for username={}: {}", transactionId, username, e.getMessage());
            return ResponseEntity.status(500).body(new LoginResponse("Internal server error"));
        }
    }
}
