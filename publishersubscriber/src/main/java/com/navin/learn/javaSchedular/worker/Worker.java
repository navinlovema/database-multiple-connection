package com.navin.learn.javaSchedular.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
@Slf4j
public class Worker implements Runnable{
    @Override
    public void run() {
        try{
            log.info("Task Execution Started by {} ", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException ex){
            log.error("Error while sleeping {} ", ex.getCause(), ex);
        }
        log.info("Task Execution Completed...");
    }
}
