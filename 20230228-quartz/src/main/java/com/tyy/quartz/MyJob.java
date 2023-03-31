package com.tyy.quartz;

import org.quartz.*;

/**
 * Job 需要是一个public的类，不然 Quartz 无法调用到他
 */

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyJob implements Job {

    /**
     * 定义一个 name 属性以及 set方法，会自动注入值，不需要主动调用 set方法
     */

    private String name;
    private int count;
    private int jobdetail_count;

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobdetail_count(int jobdetail_count) {
        this.jobdetail_count = jobdetail_count;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        JobDataMap jobDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();

        System.out.println("jobDetail - map:" + jobDataMap.getString("jobdetail-key-1"));
        System.out.println("trigger - map:" + jobDataMap1.getString("trigger-key-1"));
        System.out.println("merged - map:" + mergedJobDataMap.getString("jobdetail-key-1"));

        // 如果存在相同的值，则
        // merged 会将 trigger 上的 datamap 覆盖掉 job 上的 datamap
        System.out.println("name: " + mergedJobDataMap.getString("name"));

        // ==========================
        mergedJobDataMap.put("count", mergedJobDataMap.getInt("count") + 1);
        System.out.println("mergedJobDataMap count : " + count);

        jobDataMap.put("jobdetail_count", jobDataMap.getInt("jobdetail_count") + 1);
        System.out.println("jobDataMap jobdetail_count : " + jobdetail_count);

    }
}
