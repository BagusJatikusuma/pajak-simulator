package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.PotensiDao;
import com.bekasidev.app.dao.impl.PotensiDaoImpl;
import com.bekasidev.app.model.Potensi;
import com.bekasidev.app.service.backend.PotensiService;
import com.bekasidev.app.wrapper.PotensiJoinWrapper;
import com.bekasidev.app.wrapper.SptpdWrapper;

import java.util.Calendar;
import java.util.List;

public class PotensiServiceImpl implements PotensiService {

    private PotensiDao potensiDao = new PotensiDaoImpl();

    @Override
    public SptpdWrapper getAllPotensi(String idRestoran, String idTransaksi) {
        return setSPTPD(potensiDao.getPotensiJoinMenu(idRestoran, idTransaksi));
    }

    @Override
    public void createPotensi(List<Potensi> listPotensi) {
        Calendar cal = Calendar.getInstance();
        for(Potensi potensi : listPotensi){
            potensi.setTanggalBuat(Long.toString(cal.getTimeInMillis()));
        }

        potensiDao.createPotensi(listPotensi);
    }

    private SptpdWrapper setSPTPD(List<PotensiJoinWrapper> listPotensi) {
        double totalMakanan = 0;
        double totalMinuman = 0;
        double totalPotensiMakanan = 0;
        double totalPotensiMinuman = 0;
        SptpdWrapper sptpdWrapper = new SptpdWrapper();

        for(PotensiJoinWrapper potensi : listPotensi){
            potensi.setJumlahPotensi(potensi.getFrekuensiPotensi() * potensi.getHargaSatuan());
            if(potensi.getJenisMenu() == 0){
                sptpdWrapper.getListMakanan().add(potensi);
                totalMakanan += potensi.getHargaSatuan() * potensi.getFrekuensiPenjualan();
                totalPotensiMakanan += potensi.getJumlahPotensi();
            }else{
                sptpdWrapper.getListMinuman().add(potensi);
                totalMinuman += potensi.getHargaSatuan() * potensi.getFrekuensiPenjualan();
                totalPotensiMinuman += potensi.getJumlahPotensi();
            }
        }
        sptpdWrapper.setTotalMakanan(totalMakanan);
        sptpdWrapper.setTotalMinuman(totalMinuman);
        sptpdWrapper.setTotalPotensiMakanan(totalPotensiMakanan);
        sptpdWrapper.setTotalPotensiMinuman(totalPotensiMinuman);
        System.out.println(sptpdWrapper.getListMakanan().size());
        System.out.println(sptpdWrapper.getListMinuman().size());
        return sptpdWrapper;
    }

    @Override
    public void calculatePenjualan(List<Potensi> listPotensi){
        for(Potensi potensi : listPotensi){
            potensi.setJumlahPenjualan(potensi.getFrekuensiPenjualan()*potensi.getHargaSatuan());
        }
    }
}
