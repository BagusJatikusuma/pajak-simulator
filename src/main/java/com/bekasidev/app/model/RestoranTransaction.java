package com.bekasidev.app.model;

public class RestoranTransaction {

    private String idHotel;
    private String idTransaction;
    private double omzetRamai;
    private double omzetNormal;
    private double omzetSepi;
    private int frekuensiRamai;
    private int frekuesniNormal;
    private int frekuensiSepi;

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
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
