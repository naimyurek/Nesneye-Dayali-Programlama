package com.hns.oop.quartz;

import java.util.ArrayList;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
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
            jobBuilder.usingJobData("examDate", job.getExamDate());
            jobBuilder.usingJobData("first", job.getBaşvuruTarihiFirst());
            jobBuilder.usingJobData("last", job.getBaşvuruTarihiLast());
            jobBuilder.usingJobData("result", job.getSonuçTarihi());

            scheduler.scheduleJob(jobBuilder.build(), trigger);
            
            Job job1 = new Job() {
                @Override
                public void execute(JobExecutionContext jec) throws JobExecutionException {
                    System.out.println("efadf");
                }
            };
            
            System.out.println("Job planned: " + job.toString());
        }
    }
    
    public void stop() throws SchedulerException{
        scheduler.shutdown();
    }
    
}
