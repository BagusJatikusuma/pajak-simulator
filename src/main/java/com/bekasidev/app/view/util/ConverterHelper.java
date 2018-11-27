/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;

/**
 *
 * @author USER
 */
public class ConverterHelper {
    private static PegawaiService pegawaiService;
    public static SuratPerintah convertPersiapanWrapperIntoSuratPerintah(PersiapanWrapper persiapanWrapper) {
        pegawaiService = ServiceFactory.getPegawaiService();
        
        String idSP = String.valueOf(new Date().getTime());
        
        SuratPerintah suratPerintah = new SuratPerintah();
        suratPerintah.setIdSP(idSP);
        suratPerintah.setNomorSurat(persiapanWrapper.getNomorSurat());
        suratPerintah.setNomorUrut(persiapanWrapper.getNomorSurat());
        
        DateFormat formatter = new SimpleDateFormat("dd MMMM YYYY");
        
        if (persiapanWrapper.getTanggalPengesahan() != null)
            suratPerintah.setTanggalSurat(formatter.format(persiapanWrapper.getTanggalPengesahan()));
        
        suratPerintah.setTahunAnggaranBiaya(persiapanWrapper.getBiayaTahunAPBD());
        suratPerintah.setPemberiSK(persiapanWrapper.getPemberiSK());
        
        suratPerintah.setPemberiSP(persiapanWrapper.getPenandatangan());
        
        String masaPajakAwal = "1/"+persiapanWrapper.getMasaPajakAwalBulan()+"/"+persiapanWrapper.getMasaPajakAwalTahun();
        System.out.println("masa pajak awal "+masaPajakAwal);
        String masaPajakAkhir = "1/"+persiapanWrapper.getMasaPajakAkhirbulan()+"/"+persiapanWrapper.getMasaPajakAkhirTahun();
        System.out.println("masa pajak awal "+masaPajakAkhir);
        DateFormat dateFormatter = new SimpleDateFormat("d/MM/yyyy", Locale.forLanguageTag("id-ID"));
        
        try {
            suratPerintah.setMasaPajakAwal(
                String.valueOf(dateFormatter.parse(masaPajakAwal).getTime()));
            suratPerintah.setMasaPajakAkhir(
                String.valueOf(dateFormatter.parse(masaPajakAkhir).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(ConverterHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        suratPerintah.setTahap((short)persiapanWrapper.getTahapKe().intValue());
        suratPerintah.setLamaPelaksanaan(persiapanWrapper.getLamaPelaksanaan().shortValue());
        suratPerintah.setTempat(persiapanWrapper.getDitetapkanDi());
        suratPerintah.setNomorSK(persiapanWrapper.getDasarNomor());
        suratPerintah.setTanggalSK(String.valueOf(persiapanWrapper.getDasarTanggal().getTime()));
        suratPerintah.setTahunAnggaranSK(Integer.parseInt(persiapanWrapper.getDasarTahunAnggaran()));
        suratPerintah.setNomorSuratBiaya(persiapanWrapper.getBiayaNomorAPBD());
        suratPerintah.setTanggalBiaya(String.valueOf(persiapanWrapper.getBiayaTanggalAPBD().getTime()));
        
        //get tim
        List<TimSP> listTim = new ArrayList<>();
        for (TimWPWrapper obj : persiapanWrapper.getTimWPWrappers()) {
            List<Pegawai> anggotaTim = pegawaiService.getPegawaiByTim(obj.getTim().getIdTim());
            
            TimSP timSP = new TimSP();
            timSP.setIdSP(idSP);
            timSP.setListWP(obj.getWajibPajaks());
            timSP.setNamaTim(obj.getTim().getNamaTim());
            timSP.setPenanggungJawab(obj.getPenanggungJawab());
            timSP.setSupervisor(obj.getSupervisor());
            timSP.setListAnggota(anggotaTim);
            
            listTim.add(timSP);
        }
        suratPerintah.setListTim(listTim);
        
        return suratPerintah;
    }
    
    public static PersiapanWrapper convertSuratPerintahToPersiapanWrapper(SuratPerintah suratPerintah) {
        PersiapanWrapper persiapanWrapper = new PersiapanWrapper();
        persiapanWrapper.setDasarNomor(suratPerintah.getNomorSK());
        persiapanWrapper.setDasarTanggal(new Date(Long.valueOf(suratPerintah.getTanggalSK())));
        persiapanWrapper.setDasarTahunAnggaran(String.valueOf(suratPerintah.getTahunAnggaranSK()));
        
        persiapanWrapper.setPemberiSK(suratPerintah.getPemberiSK());
        persiapanWrapper.setPenandatangan(suratPerintah.getPemberiSP());
        
        persiapanWrapper.setMasaPajakAwalBulan(new Date(Long.valueOf(suratPerintah.getMasaPajakAwal()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue());
        persiapanWrapper.setMasaPajakAkhirbulan(new Date(Long.valueOf(suratPerintah.getMasaPajakAkhir()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue());
        persiapanWrapper.setMasaPajakAwalTahun(new Date(Long.valueOf(suratPerintah.getMasaPajakAwal()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getYear());
        persiapanWrapper.setMasaPajakAkhirTahun(new Date(Long.valueOf(suratPerintah.getMasaPajakAkhir()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getYear());

        persiapanWrapper.setTahapKe(Integer.valueOf(suratPerintah.getTahap()));
        persiapanWrapper.setBiayaTahunAPBD(suratPerintah.getTahunAnggaranBiaya());
        persiapanWrapper.setBiayaTanggalAPBD(new Date(Long.parseLong(suratPerintah.getTanggalBiaya())));
        persiapanWrapper.setBiayaNomorAPBD(suratPerintah.getNomorSuratBiaya());
        persiapanWrapper.setDitetapkanDi(suratPerintah.getTempat());
        
        persiapanWrapper.setLamaPelaksanaan(Integer.valueOf(suratPerintah.getLamaPelaksanaan()));
        
        return persiapanWrapper;
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
    
    private Integer convertBulanStringIntoInteger(String bulanString) {
        switch(bulanString) {
            case "Januari" : return 0;
            case "Februari": return 1;
            case "Maret": return 2;
            case "April": return 3;
            case "Mei": return 4;
            case "Juni": return 5;
            case "Juli": return 6;
            case "Agustus": return 7;
            case "September": return 8;
            case "Oktober": return 9;
            case "November": return 10;
            case "Desember": return 11;
        }
        return null;
    }
    
}