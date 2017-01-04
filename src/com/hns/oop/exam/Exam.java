package com.hns.oop.exam;

public class Exam {
    private String ad;
    private String sınavTarihi;
    private String başvuruTarihiFirst;
    private String başvuruTarihiLast;
    private String sonuçTarihi;
    
    public Exam(){
        this.ad = null;
        this.sınavTarihi = null;
    }
    
    public Exam(String ad, String sınavTarihi, String başvuruTarihiFirst, String başvuruTarihiLast, String sonuçTarihi){
        this.ad = ad;
        this.sınavTarihi = sınavTarihi;
        this.başvuruTarihiFirst = başvuruTarihiFirst;
        this.başvuruTarihiLast = başvuruTarihiLast;
        this.sonuçTarihi = sonuçTarihi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSınavTarihi() {
        return sınavTarihi;
    }

    public void setSınavTarihi(String sınavTarihi) {
        this.sınavTarihi = sınavTarihi;
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

    public void setBaşvuruTarihiFirst(String başvuruTarihiFirst) {
        this.başvuruTarihiFirst = başvuruTarihiFirst;
    }

    public void setBaşvuruTarihiLast(String başvuruTarihiLast) {
        this.başvuruTarihiLast = başvuruTarihiLast;
    }

    public void setSonuçTarihi(String sonuçTarihi) {
        this.sonuçTarihi = sonuçTarihi;
    }
    
    @Override
    public String toString() {
        return ad + " " + sınavTarihi;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Exam){
            Exam e = (Exam) o;
            if (e.getAd().equals(getAd()) && e.getSınavTarihi().equals(getSınavTarihi()))
                return true;
        }
        return false;
    }
    
}
