package com.bk.gym.security;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BruteForceProtector {
    private final Map<String, Integer> attempts = new ConcurrentHashMap<>();
    private final Map<String, Instant> lockout = new ConcurrentHashMap<>();
    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCK_TIME_SECONDS = 300; // 5 minutes

    public boolean isBlocked(String username) {
        if (!lockout.containsKey(username)) return false;
        if (Instant.now().isAfter(lockout.get(username).plusSeconds(LOCK_TIME_SECONDS))) {
            lockout.remove(username);
            attempts.remove(username);
            return false;
        }
        return true;
    }

    public void loginFailed(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        if (attempts.get(username) >= MAX_ATTEMPTS) {
            lockout.put(username, Instant.now());
        }
    }

    public void loginSucceeded(String username) {
        attempts.remove(username);
        lockout.remove(username);
    }
}
