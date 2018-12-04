package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.RekapitulasiDao;
import com.bekasidev.app.dao.impl.RekapitulasiDaoImpl;
import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;

import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.round;

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
    public void calculateRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper, float persentase) {
        double totalOmzetPeriksa = 0, totalPajakPeriksa = 0, totalOmzetLaporan = 0,
                totalPajakDisetor = 0, totalOmzet = 0, totalPokokPajak = 0, totalDenda = 0, totalJumlah = 0;
        for(Rekapitulasi rekapitulasi : rekapitulasiWrapper.getListRekapitulasi()){
            rekapitulasi.setPajakHasilPeriksa((double) round(rekapitulasi.getOmzetHasilPeriksa() * persentase));
            rekapitulasi.setPajakDisetor((double) round(rekapitulasi.getOmzetLaporan() * persentase));
            rekapitulasi.setOmzet(rekapitulasi.getOmzetHasilPeriksa() - rekapitulasi.getOmzetLaporan());
            rekapitulasi.setPokokPajak(rekapitulasi.getPajakHasilPeriksa() - rekapitulasi.getPajakDisetor());
            rekapitulasi.setDenda((double) round(
                    rekapitulasi.getPokokPajak() * rekapitulasi.getPersentaseDenda() * persentase * 0.1
                    ));
            rekapitulasi.setJumlah(rekapitulasi.getPokokPajak() + rekapitulasi.getDenda());

            totalOmzetPeriksa += rekapitulasi.getOmzetHasilPeriksa();
            totalPajakPeriksa += rekapitulasi.getPajakHasilPeriksa();
            totalOmzetLaporan += rekapitulasi.getOmzetLaporan();
            totalPajakDisetor += rekapitulasi.getPajakDisetor();
            totalOmzet += rekapitulasi.getOmzet();
            totalPokokPajak += rekapitulasi.getPokokPajak();
            totalDenda += rekapitulasi.getDenda();
            totalJumlah += rekapitulasi.getJumlah();
        }
        rekapitulasiWrapper.setTotalOmzetPeriksa(totalOmzetPeriksa);
        rekapitulasiWrapper.setTotalPajakPeriksa(totalPajakPeriksa);
        rekapitulasiWrapper.setTotalOmzetLaporan(totalOmzetLaporan);
        rekapitulasiWrapper.setTotalPajakDisetor(totalPajakDisetor);
        rekapitulasiWrapper.setTotalOmzet(totalOmzet);
        rekapitulasiWrapper.setTotalPokokPajak(totalPokokPajak);
        rekapitulasiWrapper.setTotalDenda(totalDenda);
        rekapitulasiWrapper.setTotalJumlah(totalJumlah);
    }

    @Override
    public void setBulanRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper, Date masaPajakAwal, Date masaPajakAkhir) {
        Calendar calAwal = Calendar.getInstance(), calAkhir = Calendar.getInstance();
        calAwal.setTime(masaPajakAwal);
        calAkhir.setTime(masaPajakAkhir);
        System.out.println("Masuk");
        int selisihTahun = calAkhir.get(Calendar.YEAR) - calAwal.get(Calendar.YEAR);
        int bulanAwal = calAwal.get(Calendar.MONTH), bulanAkhir, count = 0;
        int jmlBulan = 12*selisihTahun + (calAkhir.get(Calendar.MONTH) - bulanAwal) + 1;
        int denda = 0;
        for(int i = 0; i <= selisihTahun; i++){
            System.out.println("Masuk");
            if(i != selisihTahun) bulanAkhir = 12;
            else bulanAkhir = calAkhir.get(Calendar.MONTH)+1;
            for(int j = bulanAwal; j < bulanAkhir;j++){
                if(((jmlBulan - count) * 2) > 48) denda = 48;
                else denda = (jmlBulan - count) * 2;
                rekapitulasiWrapper.getListRekapitulasi().add(new Rekapitulasi(
                    convertBulanIntegerIntoString(j) + " " + (calAwal.get(Calendar.YEAR) + i),
                        denda
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
