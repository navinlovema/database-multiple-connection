package com.navin.learn.rateLimiter.token;

import com.navin.learn.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRateLimitInfo {
    int maxRequest = Constants.cacheLimit;
    Instant time = Instant.now();
}
