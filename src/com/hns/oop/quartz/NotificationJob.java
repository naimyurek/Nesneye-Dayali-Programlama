package com.hns.oop.quartz;

import com.hns.oop.notifier.Notifier;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public abstract class NotificationJob implements Job {
    
    protected static String cronExpression = "0 0 12 1/1 * ? *"; // Her gün saat 12'de tekrarla
    protected static Notifier notifier = null;
    
    private String name;
    private String examDate;
    private String başvuruTarihiFirst; 
    private String başvuruTarihiLast;
    private String sonuçTarihi;

    public NotificationJob(String examName, String examDate, String başvuruTarihiFirst, String başvuruTarihiLast, String sonuçTarihi) {
        this.name = examName;
        this.examDate = examDate;
        this.başvuruTarihiFirst = başvuruTarihiFirst;
        this.başvuruTarihiLast = başvuruTarihiLast;
        this.sonuçTarihi = sonuçTarihi;
    }
    
    @Override
    public abstract void execute(JobExecutionContext context) throws JobExecutionException;
    
    public Trigger getTrigger() {
        Trigger trigger = TriggerBuilder  // cron expression kullanarak oluşturulan Trigger
                            .newTrigger()
                            .withSchedule(CronScheduleBuilder.cronSchedule(NotificationJob.cronExpression))
                            .build();
        return trigger;
    }
    
    public String getName(){
        return name;
    }
    
    public String getExamDate(){
        return examDate;
    }

    public String getBaşvuruTarihiFirst() {
        return başvuruTarihiFirst;
    }

    public String getBaşvuruTarihiLast() {
        return başvuruTarihiLast;
    }

    public String getSonuçTarihi() {
        return sonuçTarihi;
    }
    
    public static void setCronExpression(String cronExpression) {
        NotificationJob.cronExpression = cronExpression;
    }

    public static void setNotifier(Notifier notifier) {
        NotificationJob.notifier = notifier;
    }
    
}
