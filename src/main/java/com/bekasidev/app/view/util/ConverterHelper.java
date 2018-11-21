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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class ConverterHelper {
    private PegawaiService pegawaiService;
    public SuratPerintah convertPersiapanWrapperIntoSuratPerintah(PersiapanWrapper persiapanWrapper) {
        pegawaiService = ServiceFactory.getPegawaiService();
        
        String idSP = String.valueOf(new Date().getTime());
        
        SuratPerintah suratPerintah = new SuratPerintah();
        suratPerintah.setIdSP(idSP);
        suratPerintah.setNomorSurat(Integer.valueOf(persiapanWrapper.getNomorSurat()));
        suratPerintah.setNomorUrut(persiapanWrapper.getNomorSurat());
        
        DateFormat formatter = new SimpleDateFormat("dd MMMM YYYY");
        suratPerintah.setTanggalSP(formatter.format(new Date()));
        suratPerintah.setTahunAnggatan(persiapanWrapper.getBiayaTahunAPBD());
        suratPerintah.setNamaPemberi(persiapanWrapper.getNama());
        suratPerintah.setJabatanPemberi(persiapanWrapper.getJabatan());
        suratPerintah.setMasaPajakAwal(
                String.valueOf(persiapanWrapper.getMasaPajakAwalBulan())
                        +String.valueOf(persiapanWrapper.getMasaPajakAwalTahun()));
        suratPerintah.setMasaPajakAkhir(
                String.valueOf(persiapanWrapper.getMasaPajakAkhirbulan())
                        +String.valueOf(persiapanWrapper.getMasaPajakAkhirTahun()));
        suratPerintah.setTahap((short)persiapanWrapper.getTahapKe().intValue());
        suratPerintah.setLamaPelaksanaan(String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
        suratPerintah.setTempat(persiapanWrapper.getDitetapkanDi());
        
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
    
    public PersiapanWrapper convertSuratPerintahToPersiapanWrapper(SuratPerintah suratPerintah) {
        PersiapanWrapper persiapanWrapper = new PersiapanWrapper();
        
        
        return persiapanWrapper;
    }
    
}
