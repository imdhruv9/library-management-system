package com.libraryManagement.libraryManagement.services.implementations;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistService {

    // ConcurrentHashMap to store blacklisted tokens
    private ConcurrentHashMap<String, Long> blacklistedTokens = new ConcurrentHashMap<>();

    // Add token to blacklist with expiration time (in milliseconds)
    public void blacklistToken(String token, long expirationTime) {
        long expirationTimestamp = System.currentTimeMillis() + expirationTime;
        blacklistedTokens.put(token, expirationTimestamp);
    }

    // Check if the token is blacklisted
    public boolean isTokenBlacklisted(String token) {
        Long expirationTimestamp = blacklistedTokens.get(token);

        if (expirationTimestamp == null) {
            return false;  // Token is not blacklisted
        }

        // Remove expired token
        if (System.currentTimeMillis() > expirationTimestamp) {
            blacklistedTokens.remove(token);
            return false;  // Token expired, no longer blacklisted
        }

        return true;  // Token is still blacklisted
    }
}
