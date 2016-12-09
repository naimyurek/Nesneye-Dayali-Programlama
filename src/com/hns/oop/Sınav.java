package com.hns.oop;

public class Sınav {
    private String ad;
    private String tarih;
    
    public Sınav(){
        this.ad = null;
        this.tarih = null;
    }
    
    public Sınav(String ad, String tarih){
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
}
