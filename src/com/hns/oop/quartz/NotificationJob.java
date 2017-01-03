package com.hns.oop.quartz;

import com.hns.oop.notifier.Notifier;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public abstract class NotificationJob implements Job {
    
    protected static String cronExpression = "0 0 12 1/1 * ? *"; // Once a day at 12
    protected static Notifier notifier = null;
    
    private String name;
    private String date;

    public NotificationJob(String examName, String examDate) {
        this.name = examName;
        this.date = examDate;
    }
    
    @Override
    public abstract void execute(JobExecutionContext context) throws JobExecutionException;
    public abstract Class getJobClass();
    
    public Trigger getTrigger() {
        Trigger trigger = TriggerBuilder
                            .newTrigger()
                            .withSchedule(CronScheduleBuilder.cronSchedule(NotificationJob.cronExpression))
                            .build();
        return trigger;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDate(){
        return date;
    }
    
    public static void setCronExpression(String cronExpression) {
        NotificationJob.cronExpression = cronExpression;
    }

    public static void setNotifier(Notifier notifier) {
        NotificationJob.notifier = notifier;
    }
    
}
