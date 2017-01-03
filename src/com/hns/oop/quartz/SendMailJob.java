package com.hns.oop.quartz;

import com.hns.oop.exceptions.NotifierException;
import com.hns.oop.notifier.Email;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendMailJob extends NotificationJob
{
    private static Email standartEmail = null;
    
    public SendMailJob() {
        super("Unknown","01.01.2001");
    }
    
    public SendMailJob(String examName, String examDate){
        super(examName, examDate);
    }

    public static void setStandartEmail(Email standartEmail) {
        SendMailJob.standartEmail = standartEmail;
    }
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        if (standartEmail == null || notifier == null)
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
            
            System.out.println("Checking exam to send mail: " + name + " " + date);
            
            if (diffDays == 1 && notifier.isaDayAgo()){
                String text = "You have an exam tomorrow!\n\n" +
                              "Exam name: " + name + "\n" +
                              "Exam date: " + date;
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
            else if (diffDays == 7 && notifier.isaWeekAgo())            
            {
                String text = "You have an exam next week!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getDate();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
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

    @Override
    public Class getJobClass() {
        return this.getClass();
    }

    @Override
    public String toString() {
        return "SendMailJob( '" + super.getName() + "', '" + super.getDate() + "' )";
    }    
    
}
