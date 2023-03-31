package com.tyy.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService 是线程池调度任务
 * 以下程序原本目的为：
 * Foo1，运行1秒，间隔2秒，再运行1秒，  // 开始时间为 12:00:00  12:00:02  12:00:4
 * Foo2，运行3秒，间隔4秒，再运行3秒，  // 开始时间为 12:00:00  12:00:04  12:00:8
 *
 * 现象：不会造成丢失任务
 */

public class ScheduleThreadPoolTest {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        PoolTask task1 = new PoolTask("Foo1", 1000);
        scheduledExecutorService.scheduleAtFixedRate(
                task1,
                0,
                2,
                TimeUnit.SECONDS
        );

        PoolTask task2 = new PoolTask("Foo2", 3000);
        scheduledExecutorService.scheduleAtFixedRate(
                task2,
                0,
                4,
                TimeUnit.SECONDS
        );

    }
}

class PoolTask implements Runnable {

    private String name;
    private int sleep_time;

    public PoolTask(String name, int sleep_time) {
        this.name = name;
        this.sleep_time = sleep_time;
    }

    @Override
    public void run() {
        System.out.println("my name is " + name + ", start time = " + new Date());
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("my name is " + name + ", end   time = " + new Date());
    }
}