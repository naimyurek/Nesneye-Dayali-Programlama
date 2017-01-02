package com.hns.oop.notifier;

import org.junit.Test;

public class NotifierTest {

    @Test
    public void testNotifyUser() throws Exception {
        System.out.println("notifyUser");
        Email email = new Email("oopnotifier@gmail.com", "harunuyar1996@gmail.com", "Mail Example", "This mail is sent form a Java program called Student Assistant.");
        Notifier instance = new EmailNotifier("oopnotifier@gmail.com", "egebilmuh");
        instance.notifyUser(email);
    }
    
}
