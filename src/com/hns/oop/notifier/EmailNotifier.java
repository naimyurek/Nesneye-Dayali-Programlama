package com.hns.oop.notifier;

import com.hns.oop.exceptions.NotifierException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotifier extends Notifier{

    private Session session;
    
    public EmailNotifier(String username, String password) {
        
        super();
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        session = Session.getInstance(
            props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );
    } // Gmail ayarları ve Gmail hesabına giriş

    @Override
    public void notifyUser(Object info) throws NotifierException{
        Email email = null;
        if (info instanceof Email){
            email = (Email) info;
        }
        else {
            throw new NotifierException("To send an email, the email object must be given as a parameter.");
        }
        
        try { // Maili oluşturmayı dene
            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(email.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getText());

            Transport.send(message); // Maili gönder

            System.out.println("Email is sent.\n" + email.toString());
        }
        catch (MessagingException e) {
            throw new NotifierException(e.getMessage());
        }
    }
    
}
