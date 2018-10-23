package com.bekasidev.app.model;

public class RestoranTransaction {

    private String idRestoran;
    private String idTransaction;
    private double omzetRamai;
    private double omzetNormal;
    private double omzetSepi;
    private int frekuensiRamai;
    private int frekuesniNormal;
    private int frekuensiSepi;
    private int frekuensiTotal;
    private double omzetTotal;

    public int getFrekuensiTotal() {
        return frekuensiTotal;
    }

    public void setFrekuensiTotal(int frekuensiTotal) {
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

    public int getFrekuensiRamai() {
        return frekuensiRamai;
    }

    public void setFrekuensiRamai(int frekuensiRamai) {
        this.frekuensiRamai = frekuensiRamai;
    }

    public int getFrekuesniNormal() {
        return frekuesniNormal;
    }

    public void setFrekuesniNormal(int frekuesniNormal) {
        this.frekuesniNormal = frekuesniNormal;
    }

    public int getFrekuensiSepi() {
        return frekuensiSepi;
    }

    public void setFrekuensiSepi(int frekuensiSepi) {
        this.frekuensiSepi = frekuensiSepi;
    }
}
