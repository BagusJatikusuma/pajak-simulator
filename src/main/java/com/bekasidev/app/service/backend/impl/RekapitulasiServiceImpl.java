package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.RekapitulasiDao;
import com.bekasidev.app.dao.impl.RekapitulasiDaoImpl;
import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;

import java.util.Calendar;
import java.util.Date;

public class RekapitulasiServiceImpl implements RekapitulasiService {

    RekapitulasiDao rekapitulasiDao = new RekapitulasiDaoImpl();

    @Override
    public void createRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper) {
        rekapitulasiDao.createRekapitulasi(rekapitulasiWrapper);
    }

    @Override
    public RekapitulasiWrapper getRekapitulasi(String idSP, String idWP) {
        return rekapitulasiDao.getRekapitulasi(idSP, idWP);
    }

    @Override
    public void calculateRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper) {

    }

    @Override
    public void createBulanRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper, Date masaPajakAwal, Date masaPajakAkhir) {
        Calendar calAwal = Calendar.getInstance(), calAkhir = Calendar.getInstance();
        calAwal.setTime(masaPajakAwal);
        calAkhir.setTime(masaPajakAkhir);
        System.out.println("Masuk");
        int selisihTahun = calAkhir.get(Calendar.YEAR) - calAwal.get(Calendar.YEAR);
        int bulanAwal = calAwal.get(Calendar.MONTH), bulanAkhir, count = 0;
        int jmlBulan = 12*selisihTahun + (calAkhir.get(Calendar.MONTH) - bulanAwal) + 1;
        for(int i = 0; i <= selisihTahun; i++){
            System.out.println("Masuk");
            if(i != selisihTahun) bulanAkhir = 12;
            else bulanAkhir = calAkhir.get(Calendar.MONTH)+1;
            for(int j = bulanAwal; j < bulanAkhir;j++){
                rekapitulasiWrapper.getListRekapitulasi().add(new Rekapitulasi(
                    convertBulanIntegerIntoString(j),
                        (jmlBulan - count) * 2
                ));
                System.out.println(convertBulanIntegerIntoString(j) + " " + (jmlBulan - count) * 2);
                count += 1;
            }
            if(i != selisihTahun) bulanAwal = 0;
        }
    }

    private String convertBulanIntegerIntoString(Integer bulanInt) {
        switch(bulanInt) {
            case 0: return "Januari";
            case 1: return "Februari";
            case 2: return "Maret";
            case 3: return "April";
            case 4: return "Mei";
            case 5: return "Juni";
            case 6: return "Juli";
            case 7: return "Agustus";
            case 8: return "September";
            case 9: return "Oktober";
            case 10: return "November";
            case 11: return "Desember";
        }
        return "";
    }

}
