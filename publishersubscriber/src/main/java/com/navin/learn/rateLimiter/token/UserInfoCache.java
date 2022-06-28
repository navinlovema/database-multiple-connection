package com.navin.learn.rateLimiter.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

@Data
@AllArgsConstructor
@Slf4j
public class UserInfoCache {
    HashMap<Integer, UserRateLimitInfo> userInfoCache;
    UserInfoCache(){
        this.userInfoCache = new HashMap<>();
    }
    public void fetchToken(int userId){
        Instant currTime = Instant.now();
        UserRateLimitInfo tempUserInfoCache= userInfoCache.computeIfAbsent(userId, k -> new UserRateLimitInfo());
        long differenceOfTime = getDifferenceInSecond(tempUserInfoCache.getTime(), currTime)/60000;
        if(differenceOfTime <= 1 && tempUserInfoCache.getMaxRequest() > 0){
            log.info("{} User is Granted to get served his Request at {}",userId, currTime);
            updateToken(userId, currTime, tempUserInfoCache.getMaxRequest()-1);
        }
        else if(differenceOfTime > 1){
            updateToken(userId, currTime, 5);
            fetchToken(userId);
        }else{
            log.error("{} User does not have enough credit to serve request at Time {}",userId, currTime);
            throw new IllegalArgumentException("User does not have enough credit to serve request");
        }
    }

    private long getDifferenceInSecond(Instant currTime, Instant prevTime){
        Instant a = Instant.ofEpochSecond(currTime.toEpochMilli());
        Instant b = Instant.ofEpochSecond(prevTime.toEpochMilli());
        Duration result = Duration.between(a,b);
        return result.getSeconds();
    }

    public synchronized void updateToken(int userId, Instant currTime, int maxRequest){
        userInfoCache.computeIfAbsent(userId, k -> new UserRateLimitInfo()).setTime(currTime);
        userInfoCache.computeIfAbsent(userId, k -> new UserRateLimitInfo()).setMaxRequest(maxRequest);
    }
}
