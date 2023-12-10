package com.example.myagent.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Slf4j
@Component
public class CommonScheduler {

    @Value("${scheduler.thread-count:2}")
    private int threadCount;

    private ScheduledExecutorService scheduler;

    @PostConstruct
    public void init() {
        scheduler = Executors.newScheduledThreadPool(threadCount,
                new CustomizableThreadFactory("scheduler-thread-pool-"));
    }

    @PreDestroy
    public void destroy() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    public ScheduledExecutorService get() {
        return scheduler;
    }
}
