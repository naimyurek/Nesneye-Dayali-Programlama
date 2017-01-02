package com.hns.oop.exam;

public class Exam {
    private String ad;
    private String tarih;
    
    public Exam(){
        this.ad = null;
        this.tarih = null;
    }
    
    public Exam(String ad, String tarih){
        this.ad = ad;
        this.tarih = tarih;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @Override
    public String toString() {
        return ad + " " + tarih;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Exam){
            Exam e = (Exam) o;
            if (e.getAd().equals(getAd()) && e.getTarih().equals(getTarih()))
                return true;
        }
        return false;
    }
    
}
