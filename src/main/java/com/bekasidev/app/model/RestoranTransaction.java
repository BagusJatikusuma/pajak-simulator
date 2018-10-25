package com.bekasidev.app.model;

public class RestoranTransaction {

    private String idRestoran;
    private String idTransaction;
    private double omzetRamai;
    private double omzetNormal;
    private double omzetSepi;
    private float frekuensiRamai;
    private float frekuesniNormal;
    private float frekuensiSepi;
    private float frekuensiTotal;
    private double omzetTotal;
    private double ratarataOmzet;
    private double pajakSetahun;
    private double pajakPerBulan;

    public RestoranTransaction(){};

    public RestoranTransaction(String idRestoran, String idTransaction, double omzetRamai, double omzetNormal, double omzetSepi, float frekuensiRamai, float frekuesniNormal, float frekuensiSepi, float frekuensiTotal, double omzetTotal, double ratarataOmzet, double pajakSetahun, double pajakPerBulan) {
        this.idRestoran = idRestoran;
        this.idTransaction = idTransaction;
        this.omzetRamai = omzetRamai;
        this.omzetNormal = omzetNormal;
        this.omzetSepi = omzetSepi;
        this.frekuensiRamai = frekuensiRamai;
        this.frekuesniNormal = frekuesniNormal;
        this.frekuensiSepi = frekuensiSepi;
        this.frekuensiTotal = frekuensiTotal;
        this.omzetTotal = omzetTotal;
        this.ratarataOmzet = ratarataOmzet;
        this.pajakSetahun = pajakSetahun;
        this.pajakPerBulan = pajakPerBulan;
    }

    public float getFrekuensiTotal() {
        return frekuensiTotal;
    }

    public void setFrekuensiTotal(float frekuensiTotal) {
        this.frekuensiTotal = frekuensiTotal;
    }

    public double getOmzetTotal() {
        return omzetTotal;
    }

    public void setOmzetTotal(double omzetTotal) {
        this.omzetTotal = omzetTotal;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdRestoran() {
        return idRestoran;
    }

    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }

    public double getOmzetRamai() {
        return omzetRamai;
    }

    public void setOmzetRamai(double omzetRamai) {
        this.omzetRamai = omzetRamai;
    }

    public double getOmzetNormal() {
        return omzetNormal;
    }

    public void setOmzetNormal(double omzetNormal) {
        this.omzetNormal = omzetNormal;
    }

    public double getOmzetSepi() {
        return omzetSepi;
    }

    public void setOmzetSepi(double omzetSepi) {
        this.omzetSepi = omzetSepi;
    }

    public float getFrekuensiRamai() {
        return frekuensiRamai;
    }

    public void setFrekuensiRamai(float frekuensiRamai) {
        this.frekuensiRamai = frekuensiRamai;
    }

    public float getFrekuesniNormal() {
        return frekuesniNormal;
    }

    public void setFrekuesniNormal(float frekuesniNormal) {
        this.frekuesniNormal = frekuesniNormal;
    }

    public float getFrekuensiSepi() {
        return frekuensiSepi;
    }

    public void setFrekuensiSepi(float frekuensiSepi) {
        this.frekuensiSepi = frekuensiSepi;
    }

    public double getRatarataOmzet() {
        return ratarataOmzet;
    }

    public void setRatarataOmzet(double ratarataOmzet) {
        this.ratarataOmzet = ratarataOmzet;
    }

    public double getPajakSetahun() {
        return pajakSetahun;
    }

    public void setPajakSetahun(double pajakSetahun) {
        this.pajakSetahun = pajakSetahun;
    }

    public double getPajakPerBulan() {
        return pajakPerBulan;
    }

    public void setPajakPerBulan(double pajakPerBulan) {
        this.pajakPerBulan = pajakPerBulan;
    }
}
