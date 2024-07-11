package com.example.demo;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.*;
@Data
@RequiredArgsConstructor
public class Domain {

    public void x(){
        System.out.println("ghghg");
    }
}


class UserLoginLimiter {
    private static final int MAX_LOGIN_ATTEMPTS = 10;
    private static final long TIME_WINDOW = 10 * 60 * 1000; // 10 minutes in milliseconds
    private Map<String, Queue<Long>> userLoginAttempts;

    public UserLoginLimiter() {
        this.userLoginAttempts = new HashMap<>();
    }

    public boolean allowLogin(String userId) {

        long currentTime = System.currentTimeMillis();
        Queue<Long> userAttempts = userLoginAttempts.getOrDefault(userId, new ArrayDeque<>());

        // Remove old login attempts
        while (!userAttempts.isEmpty() && currentTime - userAttempts.peek() > TIME_WINDOW) {
            userAttempts.poll();
        }

        if (userAttempts.size() < MAX_LOGIN_ATTEMPTS) {
            userAttempts.offer(currentTime);
            userLoginAttempts.put(userId, userAttempts);
            return true; // Allow login
        }

        return false; // Limit login
    }
}

