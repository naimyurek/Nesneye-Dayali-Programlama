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

public class SendMailJob extends NotificationJob implements Job
{
    private static Email standartEmail = null;

    public SendMailJob() {
        super(null, null, null, null, null);
    } // Boş constructor olmazsa çalışmaz.
    
    public SendMailJob(String examName, String examDate, String başvuruTarihiFirst, String başvuruTarihiLast, String sonuçTarihi){
        super(examName, examDate, başvuruTarihiFirst, başvuruTarihiLast, sonuçTarihi);
    }

    public static void setStandartEmail(Email standartEmail) {
        SendMailJob.standartEmail = standartEmail;
    } // Standart Email şablonu set ediliyor.
    
    @Override // Scheduler tarafından otomatik çalıştırılan metod
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        if (standartEmail == null || notifier == null)
            return;
        
        try {
            Map dataMap = context.getJobDetail().getJobDataMap();
            String name = (String)dataMap.get("name");
            String date = (String)dataMap.get("examDate");
            String first = (String)dataMap.get("first");
            String last = (String)dataMap.get("last");
            String result = (String)dataMap.get("result");
            
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date d1 = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormat.format(new Date()));
            Date dateExam = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            Date dateFirst = new SimpleDateFormat("dd.MM.yyyy").parse(first);
            Date dateLast = new SimpleDateFormat("dd.MM.yyyy").parse(last);
            Date dateResult = new SimpleDateFormat("dd.MM.yyyy").parse(result);
            
            // Gün farkları hesaplanıyor.
            long diffExam = Math.abs(d1.getTime() - dateExam.getTime());
            diffExam /= (24 * 60 * 60 * 1000);
            
            long diffFirst = Math.abs(d1.getTime() - dateFirst.getTime());
            diffFirst /= (24 * 60 * 60 * 1000);
            
            long diffLast = Math.abs(d1.getTime() - dateLast.getTime());
            diffLast /= (24 * 60 * 60 * 1000);
            
            long diffResult = Math.abs(d1.getTime() - dateResult.getTime());
            diffResult /= (24 * 60 * 60 * 1000);
            
            System.out.println("Checking exam to send mail: " + name);
            
            if (diffExam == 1 && notifier.isaDayAgo()){
                String text = "You have an exam tomorrow!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
            else if (diffExam == 7 && notifier.isaWeekAgo())            
            {
                String text = "You have an exam next week!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            if (diffFirst == 1 && notifier.isaDayAgo()){
                String text = "Applications for an exam start tomorrow!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
            else if (diffFirst == 7 && notifier.isaWeekAgo())            
            {
                String text = "Applications for an exam start next week!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            if (diffLast == 1 && notifier.isaDayAgo()){
                String text = "Applications for an exam ends tomorrow!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
            else if (diffLast == 7 && notifier.isaWeekAgo())            
            {
                String text = "Applications for an exam ends next week!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            if (diffResult == 1 && notifier.isaDayAgo()){
                String text = "The results of an exam will be announced tomorrow!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
                try {
                    notifier.notifyUser(new Email(standartEmail.getFrom(), standartEmail.getTo(), standartEmail.getSubject(), text));
                } 
                catch (NotifierException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
            else if (diffResult == 7 && notifier.isaWeekAgo())            
            {
                String text = "The results of an exam will be announced next week!\n\n" +
                              "Exam name: " + super.getName() + "\n" +
                              "Exam date: " + super.getExamDate() + "\n" +
                              "First application date: " + super.getBaşvuruTarihiFirst()+ "\n" +
                              "Last application date: " + super.getBaşvuruTarihiLast()+ "\n" +
                              "Results date: " + super.getSonuçTarihi();
                
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
    public String toString() {
        return "SendMailJob( '" + super.getName() + "', '" + super.getExamDate() + "', '" + super.getBaşvuruTarihiFirst() + "', '" + super.getBaşvuruTarihiLast() + "', '" + super.getSonuçTarihi() + "' )";
    }    
    
}
