package com.tyy.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestJob {


    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                // 调用 JobDataMap
                .usingJobData("jobdetail-key-1", "jobdetail-val-1")
                .usingJobData("name", "wuzhihao-jobdetail")
                .usingJobData("jobdetail_count", 0)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever()
                )
                // 调用 JobDataMap
                .usingJobData("trigger-key-1", "trigger-val-1")
                .usingJobData("name", "wuzhihao-trigger")
                .usingJobData("count", 0)
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}


//class MyJob implements Job {
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("MyJob excete : " + new Date());
//    }
//}
