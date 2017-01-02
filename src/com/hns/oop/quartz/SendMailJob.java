package com.hns.oop.quartz;

import com.hns.oop.exceptions.NotifierException;
import com.hns.oop.notifier.Email;
import com.hns.oop.notifier.Notifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class SendMailJob implements Job
{
    private String name;
    private String date;
    private static String cronExpression = "0 0 12 1/1 * ? *"; // Once a day at 12
    private static Notifier notifier = null;
    private static Email email = null;
    
    public SendMailJob() {
        this.name = "";
        this.date = "";
    }
    
    public SendMailJob(String examName, String examDate){
        this.name = examName;
        this.date = examDate;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public static void setCronExpression(String cronExpression) {
        SendMailJob.cronExpression = cronExpression;
    }

    public static void setNotifier(Notifier notifier) {
        SendMailJob.notifier = notifier;
    }

    public static void setEmail(Email email) {
        SendMailJob.email = email;
    }

    public Trigger getTrigger() {
        Trigger trigger = TriggerBuilder
                            .newTrigger()
                            .withSchedule(CronScheduleBuilder.cronSchedule(SendMailJob.cronExpression))
                            .build();
        return trigger;
    }
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (email == null || notifier == null)
            return;
        
        try {
            Map dataMap = context.getJobDetail().getJobDataMap();
            String name = (String)dataMap.get("name");
            String date = (String)dataMap.get("date");
            
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date d1 = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat.format(new Date()));
            Date d2 = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            
            long diff = Math.abs(d1.getTime() - d2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            
            if (diffDays == 1 && notifier.isaDayAgo()){
                String text = "You have an exam tomorrow!\n\n" +
                              "Exam name: " + name + "\n" +
                              "Exam date: " + date;
                try {
                    notifier.notifyUser(new Email(email.getFrom(), email.getTo(), email.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
            else if (diffDays == 7 && notifier.isaWeekAgo())            
            {
                String text = "You have an exam next week!\n\n" +
                              "Exam name: " + getName() + "\n" +
                              "Exam date: " + getDate();
                try {
                    notifier.notifyUser(new Email(email.getFrom(), email.getTo(), email.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } 
        catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }        
    }
}
