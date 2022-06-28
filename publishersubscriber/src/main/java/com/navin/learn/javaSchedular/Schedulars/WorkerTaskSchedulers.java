package com.navin.learn.javaSchedular.Schedulars;

import com.navin.learn.javaSchedular.worker.Worker;
import com.navin.learn.javaSchedular.worker.WorkerOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class WorkerTaskSchedulers {
    @Autowired
    private ThreadPoolTaskExecutor masters;

    @Autowired
    private Worker worker;

    @Autowired
    private WorkerOne workerone;

    @Scheduled(fixedRate = 1000)
    public void fixedRateScheduler(){
        masters.execute(worker);
    }

    @Scheduled(fixedDelay = 1000)
    public void fixedDelayScheduler(){
        masters.execute(workerone);
    }
}
