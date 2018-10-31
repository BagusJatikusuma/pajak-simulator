package com.bekasidev.app.wrapper;

import com.bekasidev.app.model.Potensi;

import java.util.ArrayList;
import java.util.List;

public class SptpdWrapper {
    private List<PotensiJoinWrapper> listMakanan;
    private List<PotensiJoinWrapper> listMinuman;
    private double totalMakanan;
    private double totalMinuman;
    private double totalPotensiMakanan;
    private double totalPotensiMinuman;

    public SptpdWrapper() {
        this.listMakanan = new ArrayList<>();
        this. listMinuman = new ArrayList<>();
    }

    public double getTotalPotensiMakanan() {
        return totalPotensiMakanan;
    }

    public void setTotalPotensiMakanan(double totalPotensiMakanan) {
        this.totalPotensiMakanan = totalPotensiMakanan;
    }

    public double getTotalPotensiMinuman() {
        return totalPotensiMinuman;
    }

    public void setTotalPotensiMinuman(double totalPotensiMinuman) {
        this.totalPotensiMinuman = totalPotensiMinuman;
    }

    public List<PotensiJoinWrapper> getListMakanan() {
        return listMakanan;
    }

    public void setListMakanan(List<PotensiJoinWrapper> listMakanan) {
        this.listMakanan = listMakanan;
    }

    public List<PotensiJoinWrapper> getListMinuman() {
        return listMinuman;
    }

    public void setListMinuman(List<PotensiJoinWrapper> listMinuman) {
        this.listMinuman = listMinuman;
    }

    public double getTotalMakanan() {
        return totalMakanan;
    }

    public void setTotalMakanan(double totalMakanan) {
        this.totalMakanan = totalMakanan;
    }

    public double getTotalMinuman() {
        return totalMinuman;
    }

    public void setTotalMinuman(double totalMinuman) {
        this.totalMinuman = totalMinuman;
    }
}
