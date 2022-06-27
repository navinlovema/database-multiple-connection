package com.navin.learn.tenMillionUniqueId.rateLimiter.token;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RateLimitRunner {
    public static void main(String[] args) {
        UserInfoCache userInfoCache = new UserInfoCache();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Random random = new Random();
        int low = 1;
        int high = 5;
        for (int i = 1; i <100 ; i++) {
            int userId = random.nextInt(high - low) + low;
            try{
                Thread.sleep(15000);
                executorService.execute(() -> userInfoCache.fetchToken(userId));
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        executorService.shutdown();
    }
}
