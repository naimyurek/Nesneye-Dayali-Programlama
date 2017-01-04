package com.hns.oop.notifier;

import com.hns.oop.exceptions.NotifierException;

public abstract class Notifier {
    
    private boolean aDayAgo; // Bir gün önce bildirim gitsin mi?
    private boolean aWeekAgo; // Bir hafta önce bildirim gitsin mi?
    
    protected Notifier(){
        setaDayAgo(true);
        setaWeekAgo(true);
    }
    
    public abstract void notifyUser(Object info) throws NotifierException;

    public void setaDayAgo(boolean aDayAgo) {
        this.aDayAgo = aDayAgo;
    }

    public void setaWeekAgo(boolean aWeekAgo) {
        this.aWeekAgo = aWeekAgo;
    }

    public boolean isaDayAgo() {
        return aDayAgo;
    }

    public boolean isaWeekAgo() {
        return aWeekAgo;
    }
    
}
