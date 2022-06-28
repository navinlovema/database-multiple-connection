package com.navin.learn.javaSchedular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean(value = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threads = new ThreadPoolTaskExecutor();
        threads.setCorePoolSize(10);
        threads.setMaxPoolSize(20);
        threads.setQueueCapacity(5);
        threads.setWaitForTasksToCompleteOnShutdown(true);
        threads.initialize();
        return threads;
    }
}
