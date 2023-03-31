package com.tyy.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer 是单线程调度任务，任务数量多的时候，会丢任务，运行时间不准确
 * 以下程序原本目的为：
 * Foo1，运行1秒，间隔2秒，再运行1秒，  // 开始时间为 12:00:00  12:00:02  12:00:4
 * Foo2，运行3秒，间隔4秒，再运行3秒，  // 开始时间为 12:00:00  12:00:04  12:00:8
 *
 * 现象：每个 Foo 单独运行正常，一起运行时，会丢失任务，即 Foo2 执行太久了，Foo1 被延迟执行了
 * 原因：造成这个问题的原因是单线程运行
 * 解决：运用线程池
 */

public class TimerTest {

    public static void main(String[] args) {
        /*
        Timer 开启了新的线程，Timer是多线程
        t.schedule，此处添加了任务，但是依然是在Timer的线程里，是单线程
         */
        Timer t = new Timer();

        TimerTask task1 = new FooTimerTask("Foo1", 1000);
        t.schedule(task1, new Date(), 2000);

        TimerTask task2 = new FooTimerTask("Foo2", 3000);
        t.schedule(task2, new Date(), 4000);

    }
}

class FooTimerTask extends TimerTask{

    private String name;
    private int sleep_time;

    public FooTimerTask(String name, int sleep_time) {
        this.name = name;
        this.sleep_time = sleep_time;
    }



    @Override
    public void run() {
        System.out.println("my name is " + name);
        System.out.println("start time = " + new Date());
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end time = "+ new Date());
        System.out.println("====================================");
    }
}