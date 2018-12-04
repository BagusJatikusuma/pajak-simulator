/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.BerkasPersiapanService;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenPinjamanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
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
    private static BerkasPersiapanService berkasPersiapanService;
    
    static String[] angkaTerbilang={"","Satu","Dua","Tiga","Empat","Lima","Enam","Tujuh","Delapan","Sembilan","Sepuluh","Sebelas"};

    
    public static SuratPerintah convertPersiapanWrapperIntoSuratPerintah(PersiapanWrapper persiapanWrapper) {
        pegawaiService = ServiceFactory.getPegawaiService();
        
        String idSP;
        if (persiapanWrapper.getIdSP() == null)
            idSP = String.valueOf(new Date().getTime());
        else
            idSP = persiapanWrapper.getIdSP();
        
        SuratPerintah suratPerintah = new SuratPerintah();
        suratPerintah.setIdSP(idSP);
        suratPerintah.setNomorSurat(persiapanWrapper.getNomorSurat());
        suratPerintah.setNomorUrut(persiapanWrapper.getNomorSurat());
        
        DateFormat formatter = new SimpleDateFormat("dd MMMM YYYY");
        
        if (persiapanWrapper.getTanggalPengesahan() != null)
//            suratPerintah.setTanggalSurat(formatter.format(persiapanWrapper.getTanggalPengesahan()));
            suratPerintah.setTanggalSurat(String.valueOf(persiapanWrapper.getTanggalPengesahan().getTime()));
        
        suratPerintah.setTahunAnggaranBiaya(persiapanWrapper.getBiayaTahunAPBD());
        suratPerintah.setPemberiSK(persiapanWrapper.getPemberiSK());
        
        suratPerintah.setPemberiSP(persiapanWrapper.getPenandatangan());
        
        String masaPajakAwal = "01."+(persiapanWrapper.getMasaPajakAwalBulan()+1)+"."+persiapanWrapper.getMasaPajakAwalTahun();
        System.out.println("masa pajak awal "+masaPajakAwal);
        String masaPajakAkhir = "01."+(persiapanWrapper.getMasaPajakAkhirbulan()+1)+"."+persiapanWrapper.getMasaPajakAkhirTahun();
        System.out.println("masa pajak awal "+masaPajakAkhir);
        DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        
        try {
            System.out.println("dd awal "+String.valueOf(dateFormatter.parse(masaPajakAwal).getTime()));
            System.out.println("dd akhir "+String.valueOf(dateFormatter.parse(masaPajakAkhir).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(ConverterHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            timSP.setIdTim(obj.getTim().getIdTim());
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
        berkasPersiapanService = ServiceFactory.getBerkasPersiapanService();
        
        PersiapanWrapper persiapanWrapper = new PersiapanWrapper();
        persiapanWrapper.setIdSP(suratPerintah.getIdSP());
        persiapanWrapper.setDasarNomor(suratPerintah.getNomorSK());
        persiapanWrapper.setDasarTanggal(new Date(Long.valueOf(suratPerintah.getTanggalSK())));
        persiapanWrapper.setDasarTahunAnggaran(String.valueOf(suratPerintah.getTahunAnggaranSK()));
        
        persiapanWrapper.setPemberiSK(suratPerintah.getPemberiSK());
        persiapanWrapper.setPenandatangan(suratPerintah.getPemberiSP());
        
        System.out.println("test "+new Date(Long.valueOf(suratPerintah.getMasaPajakAwal()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue());
        
        persiapanWrapper.setMasaPajakAwalBulan(new Date(Long.valueOf(suratPerintah.getMasaPajakAwal()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue()-1);
        persiapanWrapper.setMasaPajakAkhirbulan(new Date(Long.valueOf(suratPerintah.getMasaPajakAkhir()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue()-1);
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
        if (suratPerintah.getTanggalSurat() != null 
                && !suratPerintah.getTanggalSurat().equals(""))
            persiapanWrapper.setTanggalPengesahan(new Date(Long.valueOf(suratPerintah.getTanggalSurat())));
        persiapanWrapper.setNomorSurat(suratPerintah.getNomorUrut());
        
        persiapanWrapper.setLamaPelaksanaan(Integer.valueOf(suratPerintah.getLamaPelaksanaan()));
        
        ArrayList<TimWPWrapper> timWPWrappers = new ArrayList<>();
        List<NomorTanggalWajibPajakWrapper> nomorTanggalWPList = new ArrayList<>();
        List<DokumenPinjamanWajibPajakWrapper> dokumenPinjamanWajibPajakWrappers = new ArrayList<>();
        
        for (TimSP timSP : suratPerintah.getListTim()) {
            for (WajibPajak wp : timSP.getListWP()) {
                NomorTanggalWajibPajakWrapper nt = new NomorTanggalWajibPajakWrapper();
                nt.setWajibPajak(wp);
                nt.setNomorPemberitahuanPemeriksaan(wp.getNomorBerkas().getNomorSuratPemberitahuan());
                nt.setNomorPeminjamanDokumen(wp.getNomorBerkas().getNomorSuratPeminjaman());
                nt.setNomorSuratHasilPemeriksaan(wp.getNomorBerkas().getNomorSuratHasil());
                if (wp.getNomorBerkas().getTanggalSuratPemberitahuan() != null)
                    nt.setTanggalPemberitahuanPemeriksaan(new Date(Long.valueOf(wp.getNomorBerkas().getTanggalSuratPemberitahuan())));
                if (wp.getNomorBerkas().getTanggalSuratPeminjaman() != null)
                    nt.setTanggalPeminjamanDokumen(new Date(Long.valueOf(wp.getNomorBerkas().getTanggalSuratPeminjaman())));
                if (wp.getNomorBerkas().getTanggalSuratHasil() != null)
                    nt.setTanggalSuratHasilPemeriksaan(new Date(Long.valueOf(wp.getNomorBerkas().getTanggalSuratHasil())));
                if (wp.getNomorBerkas().getTanggalBeritaAcara() != null)
                    nt.setTanggalBeritaAcaraPemeriksaan(new Date(Long.valueOf(wp.getNomorBerkas().getTanggalBeritaAcara())));
                
                nomorTanggalWPList.add(nt);
                
                if (wp.getListPinjaman().isEmpty()) {
                    String masaPajakAwal 
                            = convertBulanIntegerIntoString(new Date(Long.valueOf(suratPerintah.getMasaPajakAwal()))
                                                                    .toInstant()
                                                                    .atZone(ZoneId.systemDefault())
                                                                    .toLocalDate()
                                                                    .getMonthValue()-1)
                                +" "
                                +new Date(Long.valueOf(suratPerintah.getMasaPajakAwal()))
                                                                    .toInstant()
                                                                    .atZone(ZoneId.systemDefault())
                                                                    .toLocalDate()
                                                                    .getYear();
                    String masaPajakAkhir
                            = convertBulanIntegerIntoString(new Date(Long.valueOf(suratPerintah.getMasaPajakAkhir()))
                                                                    .toInstant()
                                                                    .atZone(ZoneId.systemDefault())
                                                                    .toLocalDate()
                                                                    .getMonthValue()-1)
                                +" "
                                +new Date(Long.valueOf(suratPerintah.getMasaPajakAkhir()))
                                                                    .toInstant()
                                                                    .atZone(ZoneId.systemDefault())
                                                                    .toLocalDate()
                                                                    .getYear();
                    berkasPersiapanService
                            .getDokumenPinjaman(wp, masaPajakAwal, masaPajakAkhir);
                }
                dokumenPinjamanWajibPajakWrappers
                        .add(new DokumenPinjamanWajibPajakWrapper(wp, wp.getListPinjaman()));
            }
            
            TimWPWrapper timWPWrapper 
                    = new TimWPWrapper();
            timWPWrapper.setPenanggungJawab(timSP.getPenanggungJawab());
            timWPWrapper.setSupervisor(timSP.getSupervisor());
            timWPWrapper.setWajibPajaks(timSP.getListWP());
            Tim tim = new Tim(timSP.getIdTim(), timSP.getNamaTim());
            timWPWrapper.setTim(tim);
            
            timWPWrappers.add(timWPWrapper);
        }
        
        persiapanWrapper.setTimWPWrappers(timWPWrappers);
        persiapanWrapper.setNomorTanggalWPList(nomorTanggalWPList);
        persiapanWrapper.setDokumenPinjamanWajibPajakWrappers(dokumenPinjamanWajibPajakWrappers);
        
        System.out.println("Test converter");
        for (NomorTanggalWajibPajakWrapper obj : persiapanWrapper.getNomorTanggalWPList()) {
            System.out.println("-- obj -- "+obj.getNomorPemberitahuanPemeriksaan());
        }
        
        return persiapanWrapper;
    }
    
    
    public static String angkaToTerbilang(Long angka){
        if(angka < 12)
        return angkaTerbilang[angka.intValue()];
        if(angka >=12 && angka <= 19)
        return angkaTerbilang[angka.intValue() % 10] + " Belas";
        if(angka >= 20 && angka <= 99)
        return angkaToTerbilang(angka / 10) + " Puluh " + angkaTerbilang[angka.intValue() % 10];
        if(angka >= 100 && angka <= 199)
        return "Seratus " + angkaToTerbilang(angka % 100);
        if(angka >= 200 && angka <= 999)
        return angkaToTerbilang(angka / 100) + " Ratus " + angkaToTerbilang(angka % 100);
        if(angka >= 1000 && angka <= 1999)
        return "Seribu " + angkaToTerbilang(angka % 1000);
        if(angka >= 2000 && angka <= 999999)
        return angkaToTerbilang(angka / 1000) + " Ribu " + angkaToTerbilang(angka % 1000);
        if(angka >= 1000000 && angka <= 999999999)
        return angkaToTerbilang(angka / 1000000) + " Juta " + angkaToTerbilang(angka % 1000000);
        if(angka >= 1000000000 && angka <= 999999999999L)
        return angkaToTerbilang(angka / 1000000000) + " Milyar " + angkaToTerbilang(angka % 1000000000);
        if(angka >= 1000000000000L && angka <= 999999999999999L)
        return angkaToTerbilang(angka / 1000000000000L) + " Triliun " + angkaToTerbilang(angka % 1000000000000L);
        if(angka >= 1000000000000000L && angka <= 999999999999999999L)
        return angkaToTerbilang(angka / 1000000000000000L) + " Quadrilyun " + angkaToTerbilang(angka % 1000000000000000L);
        return "";
    }
    
    public static String toRoman(int num){
        String[] romanCharacters = { "M", "CM", "D", "C", "XC", "L", "X", "IX", "V", "I" };
        int[] romanValues = { 1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
        String result = "";

        for (int i = 0; i < romanValues.length; i++)
        {
         int numberInPlace = num / romanValues[i];
         if (numberInPlace == 0) continue;
         result += numberInPlace == 4 && i > 0? romanCharacters[i] + romanCharacters[i - 1]:
         new String(new char[numberInPlace]).replace("\0",romanCharacters[i]);
         num = num % romanValues[i];
        }
        return result;
    }
    
    public static String convertBulanIntegerIntoString(Integer bulanInt) {
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
    
    public static Integer convertBulanStringIntoInteger(String bulanString) {
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
    
    public static String convertToTitleCaseIteratingChars(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }
    
}
