package com.hns.oop.quartz;

import java.util.ArrayList;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class NotificationScheduler {
    
    private ArrayList<NotificationJob> jobs;
    private Scheduler scheduler;
    
    public NotificationScheduler(ArrayList<NotificationJob> jobs){
        this.jobs = jobs;
    }
    
    public void start() throws SchedulerException {
        
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        this.scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        
        JobBuilder jobBuilder;

        for (NotificationJob job : jobs) {

            Trigger trigger = job.getTrigger();
            jobBuilder = JobBuilder.newJob(job.getClass());
            jobBuilder.usingJobData("name", job.getName());
            jobBuilder.usingJobData("date", job.getDate());

            scheduler.scheduleJob(jobBuilder.build(), trigger);
            
            System.out.println("Job planned: " + job.toString());
        }
    }
    
    public void stop() throws SchedulerException{
        scheduler.shutdown();
    }
    
}
