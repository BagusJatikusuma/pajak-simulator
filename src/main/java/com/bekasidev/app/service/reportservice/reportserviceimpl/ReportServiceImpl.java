/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.service.reportservice.reportserviceimpl;

import com.bekasidev.app.model.*;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.service.backend.impl.PegawaiServiceImpl;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
import com.bekasidev.app.viewfx.javafxapplication.model.AnggotaDanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaporanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapperJasper;
import com.bekasidev.app.viewfx.javafxapplication.model.SPColumnPelaporan;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapperJasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Bayu Arafli
 */
public class ReportServiceImpl implements ReportService {
    private JasperReport jasperReport;
    private ConverterHelper converterHelper = new ConverterHelper();
    
    @Override
    public void createPersiapanPajakRestoranReport(
            PersiapanWrapperJasper persiapanWrapper, WajibPajak wp) {
        try {
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://ReportPeminjamanBuku.jasper";
            String jrxmlPathFile = "D://ReportPeminjamanBuku.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            DataBeanList DataBeanList = new DataBeanList();
            ArrayList<WajibPajakModelView> dataList = new ArrayList();

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dataList);

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_restoran");
            
            System.out.println("npwpd " + wp.getNpwpd());
            parameter.put("nomor_surat", "");
            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            parameter.put("tanggal_surat", df.format(new Date()));
            parameter.put("wajib_pajak", wp);
            parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
            parameter.put("tanggal_sp", df.format(persiapanWrapper.getTanggalPengesahan()));
            parameter.put("ttd_sp", persiapanWrapper.getJabatan());
            parameter.put("hari", persiapanWrapper.getLamaPelaksanaan());
            switch(wp.getJenisWp()){
                case 0: parameter.put("jenis_wp", "Restoran");break;
                case 1: parameter.put("jenis_wp", "Hotel");break;
            }
            
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    new JRBeanCollectionDataSource(new ArrayList<Object>()));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/ReportPeminjamanBukuRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void createPersiapanPajakRestoranReport1(
            PersiapanWrapperJasper persiapanWrapper, WajibPajak wp, 
            TimWPWrapperJasper timWP) {
        try {
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://ReportPemberitahuanPemeriksaan.jasper";
            String jrxmlPathFile = "D://ReportPemberitahuanPemeriksaan.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
            
            jrxmlPathFile = "D://ReportPemberitahuanPemeriksaan_subreport1.jrxml";
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            DataBeanList DataBeanList = new DataBeanList();
            PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_restoran");
//            List<AnggotaDanWajibPajakWrapper> peg = new ArrayList<>();
//            peg.add(new AnggotaDanWajibPajakWrapper(
//                    "qwe", "qwe", (short) 0,"123", "123123", "BAKA", "IV", "PRANATA", "Anggota"));
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(timWP.getWajibPajaks());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
//            System.out.println("asdasdasdasdsaadsasd" + persiapanPajakPOJO.getWajibPajak().getNamaWP());
            parameter.put("nomor_surat", "");
            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            parameter.put("tanggal_surat", df.format(new Date()));
            parameter.put("wajib_pajak", wp);
            parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
            parameter.put("tanggal_sp", df.format(persiapanWrapper.getTanggalPengesahan()));
            parameter.put("ttd_sp", persiapanWrapper.getJabatan());
            parameter.put("hari", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            switch(wp.getJenisWp()){
                case 0: parameter.put("jenis_wp", "Restoran");break;
                case 1: parameter.put("jenis_wp", "Hotel");break;
            }
            
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun());
            parameter.put("tim", timWP.getNamaTim());
            
            parameter.put("anggota_tim", timWP.getWajibPajakJasper());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, timWP.getWajibPajakJasper());
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/ReportPemberitahuanPemeriksaanRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void createPersiapanDokumenPinjaman(WP wp, WajibPajak wajibPajak,
            PersiapanWrapperJasper persiapanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\DaftarBukuPinjaman.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\DaftarBukuPinjaman.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            String masaAwal = converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun();
            String masaAkhir = converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun();
            if(wajibPajak.getListPinjaman().size() == 0)
                ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(
                    wajibPajak, masaAwal, masaAkhir);

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(wajibPajak.getListPinjaman());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            System.out.println("asdasdasdasdsaadsasd" + wajibPajak.getListPinjaman().size());
            
            parameter.put("wajib_pajak", wajibPajak);
            
            
            parameter.put("buku_peminjaman", beanColDataSource);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/DaftarBukuPinjamanRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void createPersiapanPajakHotelReport() {
        try {
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://ReportPeminjamanBuku.jasper";
            String jrxmlPathFile = "D://ReportPeminjamanBuku.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            DataBeanList DataBeanList = new DataBeanList();
            ArrayList<WajibPajakModelView> dataList = new ArrayList();

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dataList);

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_hotel");
            System.out.println("asdasdasdasdsaadsasd" + persiapanPajakPOJO.getWajibPajak().getNamaWP());
            parameter.put("nomor_surat", persiapanPajakPOJO.getNomorUrutSurat());
            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            parameter.put("tanggal_surat", df.format(new Date()));
            parameter.put("wajib_pajak", persiapanPajakPOJO.getWajibPajak());
            parameter.put("nomor_sp", persiapanPajakPOJO.getNomorSP());
            parameter.put("tanggal_sp", persiapanPajakPOJO.getTanggalTurunSP());
            parameter.put("ttd_sp", persiapanPajakPOJO.getSpDari());
            parameter.put("hari", persiapanPajakPOJO.getLamaPemeriksaan());
            parameter.put("jenis_wp", "Hotel");
            parameter.put("pajak_awal", persiapanPajakPOJO.getMasaPajakBulanAwal() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAwal());
            parameter.put("pajak_akhir", persiapanPajakPOJO.getMasaPajakBulanAkhir()
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAkhir());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    new JRBeanCollectionDataSource(new ArrayList<Object>()));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/hotel/ReportPeminjamanBukuHotel.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void createPersiapanPajakParkirReport() {
        
    }

    @Override
    public void createPersiapanPajakHotelReport1() {
        try {
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://ReportPemberitahuanPemeriksaan.jasper";
            String jrxmlPathFile = "D://ReportPemberitahuanPemeriksaan.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            DataBeanList DataBeanList = new DataBeanList();
            PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_hotel");

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            System.out.println("asdasdasdasdsaadsasd" + persiapanPajakPOJO.getWajibPajak().getNamaWP());
            parameter.put("nomor_surat", persiapanPajakPOJO.getNomorUrutSurat());
            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            parameter.put("tanggal_surat", df.format(new Date()));
            parameter.put("wajib_pajak", persiapanPajakPOJO.getWajibPajak());
            parameter.put("nomor_sp", persiapanPajakPOJO.getNomorSP());
            parameter.put("tanggal_sp", persiapanPajakPOJO.getTanggalTurunSP());
            parameter.put("ttd_sp", persiapanPajakPOJO.getSpDari());
            parameter.put("hari", persiapanPajakPOJO.getLamaPemeriksaan());
            parameter.put("jenis_wp", "Restoran");
            parameter.put("pajak_awal", persiapanPajakPOJO.getMasaPajakBulanAwal() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAwal());
            parameter.put("pajak_akhir", persiapanPajakPOJO.getMasaPajakBulanAkhir()
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAkhir());
            parameter.put("tim", persiapanPajakPOJO.getTimPemeriksa().getNamaTim());
            
            parameter.put("anggota_tim", beanColDataSource);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/hotel/ReportPemberitahuanPemeriksaanHotel.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void createPersiapanDokumenPinjamanHotel() {
        try {
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://DaftarBukuPinjaman.jasper";
            String jrxmlPathFile = "D://DaftarBukuPinjaman.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_hotel");

            BerkasPersiapan bp = new BerkasPersiapan();
            bp.setMasaPajakAwal(persiapanPajakPOJO.getMasaPajakBulanAwal() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAwal());
            bp.setMasaPajakAkhir(persiapanPajakPOJO.getMasaPajakBulanAkhir() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAkhir());
//            ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(bp, WP.HOTEL);

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(bp.getListPinjaman());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
//            System.out.println("asdasdasdasdsaadsasd" + bp.getListPinjaman().size());
            
            parameter.put("nomor_surat", persiapanPajakPOJO.getNomorUrutSurat());
            DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            parameter.put("tanggal_surat", df.format(new Date()));
            parameter.put("wajib_pajak", persiapanPajakPOJO.getWajibPajak());
            parameter.put("nomor_sp", persiapanPajakPOJO.getNomorSP());
            parameter.put("tanggal_sp", persiapanPajakPOJO.getTanggalTurunSP());
            parameter.put("ttd_sp", persiapanPajakPOJO.getSpDari());
            parameter.put("hari", persiapanPajakPOJO.getLamaPemeriksaan());
            parameter.put("jenis_wp", "Restoran");
            parameter.put("pajak_awal", persiapanPajakPOJO.getMasaPajakBulanAwal() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAwal());
            parameter.put("pajak_akhir", persiapanPajakPOJO.getMasaPajakBulanAkhir()
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAkhir());
            parameter.put("tim", "Tim 1");
            
            parameter.put("buku_peminjaman", beanColDataSource);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/hotel/DaftarBukuPinjamanHotel.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPerintah() {
        try {
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
            
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                System.out.print("test"); 
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPerintah.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPerintah.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(persiapanWrapper.getTimWPWrappers());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_pengesahan = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_dasar_tanggal = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_biaya_tanggal_apbd = new SimpleDateFormat(pattern, id);
            
            if((persiapanWrapper.getNomorSurat() == null || persiapanWrapper.getNomorSurat().equals("")) && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_pengesahan", "    ");
            } else {
                parameter.put("nomor_surat", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_pengesahan", String.valueOf(df_tanggal_pengesahan.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("pemberi_sk", persiapanWrapper.getPemberiSK());
            parameter.put("dasar_nomor", persiapanWrapper.getDasarNomor());
            parameter.put("dasar_tanggal", String.valueOf(df_dasar_tanggal.format(persiapanWrapper.getDasarTanggal())));
            parameter.put("dasar_tahun_anggaran", persiapanWrapper.getDasarTahunAnggaran());
            parameter.put("nama_perintah", persiapanWrapper.getNama());
            parameter.put("jabatan_perintah", persiapanWrapper.getJabatan());
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAwalBulan())
                    + " " + String.valueOf(persiapanWrapper.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAkhirbulan())
                    + " " + String.valueOf(persiapanWrapper.getMasaPajakAkhirTahun()));
            parameter.put("tahap_ke", new ConverterHelper().toRoman(persiapanWrapper.getTahapKe()));
            parameter.put("lama_pelaksanaan", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            parameter.put("lama_pelaksanaan_terbilang", "( " + new ConverterHelper().angkaToTerbilang(Long.valueOf(persiapanWrapper.getLamaPelaksanaan())) + " )");
            parameter.put("biaya_tahun_apbd", String.valueOf(persiapanWrapper.getBiayaTahunAPBD()));
            parameter.put("biaya_nomor_apbd", persiapanWrapper.getBiayaNomorAPBD());
            parameter.put("biaya_tanggal_apbd", String.valueOf(df_biaya_tanggal_apbd.format(persiapanWrapper.getBiayaTanggalAPBD())));
            parameter.put("ditetapkan_di", persiapanWrapper.getDitetapkanDi());
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            parameter.put("jabatan_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getJabatanDinas()));
            parameter.put("pangkat_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getPangkat()));
            parameter.put("jabatan_penandatangan_ttd", persiapanWrapper.getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("nama_penandatangan", persiapanWrapper.getPenandatangan().getNamaPegawai().toUpperCase());

            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPerintah.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (Exception ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createDaftarPetugasPemeriksa() {
        try {
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
            
            //data dummi
            PersiapanWrapperJasper dummi = new PersiapanWrapperJasper();

            dummi.setNomorSurat(persiapanWrapper.getNomorSurat());
            dummi.setTanggalPengesahan(persiapanWrapper.getTanggalPengesahan());
            dummi.setDasarNomor(persiapanWrapper.getDasarNomor());
            dummi.setDasarTanggal(persiapanWrapper.getDasarTanggal());
            dummi.setDasarTahunAnggaran(persiapanWrapper.getDasarTahunAnggaran());
            dummi.setNama(persiapanWrapper.getNama());
            dummi.setJabatan(persiapanWrapper.getJabatan());

            dummi.setMasaPajakAwalBulan(persiapanWrapper.getMasaPajakAwalBulan());
            dummi.setMasaPajakAwalTahun(persiapanWrapper.getMasaPajakAwalTahun());
            dummi.setMasaPajakAkhirbulan(persiapanWrapper.getMasaPajakAkhirbulan());
            dummi.setMasaPajakAkhirTahun(persiapanWrapper.getMasaPajakAkhirTahun());
            dummi.setTahapKe(persiapanWrapper.getTahapKe());

            dummi.setLamaPelaksanaan(persiapanWrapper.getLamaPelaksanaan());
            dummi.setBiayaNomorAPBD(persiapanWrapper.getBiayaNomorAPBD());
            dummi.setBiayaTahunAPBD(persiapanWrapper.getBiayaTahunAPBD());
            dummi.setBiayaTanggalAPBD(persiapanWrapper.getBiayaTanggalAPBD());

            dummi.setDitetapkanDi(persiapanWrapper.getDitetapkanDi());

            dummi.setPenandatangan(persiapanWrapper.getPenandatangan());

            PegawaiService pegawaiService = ServiceFactory.getPegawaiService();

            for (TimWPWrapper tim : persiapanWrapper.getTimWPWrappers()) {

                List<Pegawai> anggotaTimList = pegawaiService.getPegawaiByTim(tim.getTim().getIdTim());
                List<AnggotaDanWajibPajakWrapper> wajibPajakList = new ArrayList<AnggotaDanWajibPajakWrapper>();

                int jumlah = anggotaTimList.size();
                if(jumlah < tim.getWajibPajaks().size()){
                    jumlah = tim.getWajibPajaks().size();
                }

                for (int i = 0; i < jumlah; i++) {
                    AnggotaDanWajibPajakWrapper wp = new AnggotaDanWajibPajakWrapper();

                    if(i < tim.getWajibPajaks().size()){
                        wp.setIdWajibPajak(tim.getWajibPajaks().get(i).getNpwpd());
                        wp.setNamaWajibPajak(tim.getWajibPajaks().get(i).getNamaWajibPajak());
                        wp.setJenisWp(tim.getWajibPajaks().get(i).getJenisWp());
                    } else {
                        wp.setIdWajibPajak("");
                        wp.setNamaWajibPajak("");
                        wp.setJenisWp((short) -1);
                    }

                    if (i < anggotaTimList.size()) {
                        wp.setIdTim(anggotaTimList.get(i).getIdTim());
                        wp.setNipPegawai(anggotaTimList.get(i).getNipPegawai());
                        wp.setNamaPegawai(anggotaTimList.get(i).getNamaPegawai());
                        wp.setPangkat(anggotaTimList.get(i).getPangkat());
                        wp.setGolongan(anggotaTimList.get(i).getGolongan());
                        wp.setJabatanTim(anggotaTimList.get(i).getJabatanTim());
                    } else {
                        wp.setIdTim("");
                        wp.setNipPegawai("");
                        wp.setNamaPegawai("");
                        wp.setPangkat("");
                        wp.setGolongan("");
                    }

                    wajibPajakList.add(wp);
                }

                TimWPWrapperJasper objTimWPWrapper
                        = new TimWPWrapperJasper(
                                tim.getPenanggungJawab().getNipPegawai(),
                                tim.getPenanggungJawab().getNamaPegawai(),
                                tim.getPenanggungJawab().getPangkat(),
                                tim.getPenanggungJawab().getGolongan(),
                                tim.getPenanggungJawab().getJabatanTim(),

                                tim.getSupervisor().getNipPegawai(),
                                tim.getSupervisor().getNamaPegawai(),
                                tim.getSupervisor().getPangkat(),
                                tim.getSupervisor().getGolongan(),
                                tim.getSupervisor().getJabatanTim(),

                                tim.getTim().getNamaTim(),
                                wajibPajakList
                        );
                JRBeanCollectionDataSource beanColDataSourceWp =
                        new JRBeanCollectionDataSource(wajibPajakList);
                objTimWPWrapper.setWajibPajakJasper(beanColDataSourceWp);
                objTimWPWrapper.setListWP(tim.getWajibPajaks());

                dummi.getTimWPWrapperJaspers().add(objTimWPWrapper);

            }


            //data dummi

            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\DaftarPetugasPemeriksa.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\DaftarPetugasPemeriksa.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);

            JasperReport report = null;

            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (TimWPWrapperJasper obj : dummi.getTimWPWrapperJaspers()) {
                System.out.println(obj.getNamaPegawaiPenanggungJawab());
                System.out.println(obj.getNamaPegawaiSupervisor());
                System.out.println(obj.getNamaTim());
            }

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dummi.getTimWPWrapperJaspers());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_pengesahan = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_dasar_tanggal = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_biaya_tanggal_apbd = new SimpleDateFormat(pattern, id);
            

            if((dummi.getNomorSurat() == null || dummi.getNomorSurat().equals("")) && dummi.getTanggalPengesahan() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_pengesahan", "    ");
            } else {
                parameter.put("nomor_surat", dummi.getNomorSurat());
                parameter.put("tanggal_pengesahan", String.valueOf(df_tanggal_pengesahan.format(dummi.getTanggalPengesahan())));
            }
            
            parameter.put("dasar_nomor", dummi.getDasarNomor());
            parameter.put("dasar_tanggal", String.valueOf(df_dasar_tanggal.format(dummi.getDasarTanggal())));
            parameter.put("dasar_tahun_anggaran", dummi.getDasarTahunAnggaran());
            parameter.put("nama_perintah", dummi.getNama());
            parameter.put("jabatan_perintah", dummi.getJabatan());
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(dummi.getMasaPajakAwalBulan())
                    + " " + String.valueOf(dummi.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(dummi.getMasaPajakAkhirbulan())
                    + " " + String.valueOf(dummi.getMasaPajakAkhirTahun()));
            parameter.put("tahap_ke", String.valueOf(dummi.getTahapKe()));
            parameter.put("lama_pelaksanaan", String.valueOf(dummi.getLamaPelaksanaan()));
            parameter.put("biaya_tahun_apbd", String.valueOf(dummi.getBiayaTahunAPBD()));
            parameter.put("biaya_nomor_apbd", dummi.getBiayaNomorAPBD());
            parameter.put("biaya_tanggal_apbd", String.valueOf(df_biaya_tanggal_apbd.format(dummi.getBiayaTanggalAPBD())));
            parameter.put("ditetapkan_di", dummi.getDitetapkanDi());
            parameter.put("penandatangan", dummi.getPenandatangan());

            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report,
                    parameter,
                    beanColDataSource);

            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/DaftarPetugasPemeriksa.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }

                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);

        } catch (Exception ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createTandaTerima(WP wp, WajibPajak wajibPajak, PersiapanWrapperJasper persiapanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\TandaTerima.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\TandaTerima.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
      
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            parameter.put("wajib_pajak_nama", wajibPajak.getNamaWajibPajak());
            parameter.put("wajib_pajak_npwpd", wajibPajak.getNpwpd());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/TandaTerima.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createQuesionerRestoran() {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\QuesionerRestorann.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\QuesionerRestorann.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/QuesionerRestorann.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void createPersiapanPeminjamanBuku(PersiapanWrapper persiapanWrapper, WajibPajak wp, int index) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPeminjamanBuku.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPeminjamanBuku.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(persiapanWrapper.getTimWPWrappers());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            if((persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPeminjamanDokumen() == null || persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPeminjamanDokumen().equals("")) && 
                    persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPeminjamanDokumen() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPeminjamanDokumen());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPeminjamanDokumen())));
            }
            

            if((persiapanWrapper.getNomorSurat() == null || persiapanWrapper.getNomorSurat().equals("")) && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            parameter.put("wajib_pajak", wp);
            switch(wp.getJenisWp()){
                case 0: parameter.put("jenis_wp", "Restoran");break;
                case 1: parameter.put("jenis_wp", "Hotel");break;
            }

            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/ReportPeminjamanBukuRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createPemberitahuanPemeriksaan(PersiapanWrapper persiapanWrapper, WajibPajak wp, TimWPWrapperJasper timWP, int index) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPemberitahuanPemeriksaan.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPemberitahuanPemeriksaan.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
            
//            jrxmlPathFile = "D://ReportPemberitahuanPemeriksaan_subreport1.jrxml";
//            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(timWP.getWajibPajaks());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
//            System.out.println("asdasdasdasdsaadsasd" + persiapanPajakPOJO.getWajibPajak().getNamaWP());
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            if((persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPemberitahuanPemeriksaan() == null || persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPemberitahuanPemeriksaan().equals("")) && 
                    persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPemberitahuanPemeriksaan() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPemberitahuanPemeriksaan());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPemberitahuanPemeriksaan())));
            }
            
            
            parameter.put("wajib_pajak", wp);
            
            if((persiapanWrapper.getNomorSurat() == null || persiapanWrapper.getNomorSurat().equals("")) && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            parameter.put("jabatan_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getJabatanDinas()));
            parameter.put("pangkat_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getPangkat()));
            parameter.put("jabatan_penandatangan_ttd", persiapanWrapper.getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("nama_penandatangan", persiapanWrapper.getPenandatangan().getNamaPegawai().toUpperCase());
            
            System.out.println(converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getJabatanDinas()));
            System.out.println(converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getPangkat()));
            System.out.println(persiapanWrapper.getPenandatangan().getJabatanDinas().toUpperCase());
            System.out.println(persiapanWrapper.getPenandatangan().getNamaPegawai().toUpperCase());
            
            parameter.put("hari", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            switch(wp.getJenisWp()){
                case 0: parameter.put("jenis_wp", "Restoran");break;
                case 1: parameter.put("jenis_wp", "Hotel");break;
            }
            
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun());
            parameter.put("tim", timWP.getNamaTim());
            
            parameter.put("anggota_tim", timWP.getWajibPajakJasper());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, timWP.getWajibPajakJasper());
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/ReportPemberitahuanPemeriksaanRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPernyataan1(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPernyataan(1).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPernyataan(1).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            if((pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null || pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat().equals("")) && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "800/(nomor)/BAPENDA");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", "800/" + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() + "/BAPENDA");
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
            }
            
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPernyataan(1).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createTandaTerimaSPHP2(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratTandaTerima(2).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratTandaTerima(2).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            parameter.put("penandatangan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas());
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            if((pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null || pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat().equals("")) && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "800/(nomor)/BAPENDA");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", "800/" + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() + "/BAPENDA");
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
            }
            
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratTandaTerima(2).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPersetujuan4(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPersetujuan(4).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPersetujuan(4).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            
            parameter.put("penandatangan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas());
            
            SimpleDateFormat df_tanggal_sphp = new SimpleDateFormat(pattern, id);
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil()== null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil().equals(""))){
                parameter.put("nomor_sphp", "973/   /BAPENDA");
                parameter.put("tanggal_sphp", "    ");
            } else {
                parameter.put("nomor_sphp", "973/" + pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil()+"/BAPENDA");
                Long longTanggalSurat = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil());
                Date tanggalSurat = new Date(longTanggalSurat);
                parameter.put("tanggal_sphp", String.valueOf(df_tanggal_sphp.format(tanggalSurat)));
            }
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPersetujuan(4).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createPernyataanPersetujuanHasilPemeriksaan5(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\PernyataanPersetujuanHasilPemeriksaan(5).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\PernyataanPersetujuanHasilPemeriksaan(5).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sphp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());

            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil()== null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil().equals(""))){
                parameter.put("nomor_sphp", "973/   /BAPENDA");
                parameter.put("tanggal_sphp", "    ");
            } else {
                parameter.put("nomor_sphp", "973/" + pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil()+"/BAPENDA");
                Long longTanggalSurat = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil());
                Date tanggalSurat = new Date(longTanggalSurat);
                parameter.put("tanggal_sphp", String.valueOf(df_tanggal_sphp.format(tanggalSurat)));
            }
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/PernyataanPersetujuanHasilPemeriksaan(5).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPenyetaanKesanggupanMembayarPajakKurangBarang(6).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPenyetaanKesanggupanMembayarPajakKurangBarang(6).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            Long terbilang = pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah().longValue();
            parameter.put("jumlah_rekapitulasi", "Rp. " + new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah()) + ",- (" + new ConverterHelper().angkaToTerbilang(terbilang) + " Rupiah)");
            
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPenyetaanKesanggupanMembayarPajakKurangBarang(6).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPernyataan7(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPernyataan(7).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPernyataan(7).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPernyataan(7).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void createKertasPemeriksaanPajak(PelaksanaanWrapper pelaksanaanWrapper, TimSP timSP) {
        try{
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\KertasPemeriksaanPajak.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\KertasPemeriksaanPajak.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            JRBeanCollectionDataSource beanColDataSource =
//            new JRBeanCollectionDataSource(pelaksanaanWrapper.getRekapitulasiWrapper().getListRekapitulasi());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wp", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()).toUpperCase() + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()).toUpperCase() + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            parameter.put("total_omzetp", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalOmzetPeriksa());
            parameter.put("total_pajakp", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPajakPeriksa());
            parameter.put("total_omzetl", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalOmzetLaporan());
            parameter.put("total_pajak_disetor", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPajakDisetor());
            parameter.put("total_omzet", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalOmzet());
            parameter.put("total_pokokp", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPokokPajak());
            parameter.put("total_denda", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalDenda());
            parameter.put("total_jumlah", pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah());
            
            parameter.put("pemeriksaan_pajak", new JRBeanCollectionDataSource(pelaksanaanWrapper.getRekapitulasiWrapper().getListRekapitulasi()));
            parameter.put("anggota_tim", new JRBeanCollectionDataSource(timSP.getListAnggota()));
            
            for (Pegawai pegawai : timSP.getListAnggota()) {
                System.out.println("nama pegawai : " + pegawai.getNamaPegawai());
            }
            
            parameter.put("supervisor_nama", timSP.getSupervisor().getNamaPegawai());
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0 : parameter.put("persen", "PAJAK RESTORAN (10%)"); break;
                case 1 : parameter.put("persen", "PAJAK HOTEL (10%)"); break;
                case 2 : parameter.put("persen", "PAJAK PARKIR (25%)"); break;
            }
            
            
            parameter.put("tanggung_jawab_nama", timSP.getSupervisor().getNamaPegawai());
            parameter.put("tanggung_jawab_nip", timSP.getSupervisor().getNipPegawai());
            parameter.put("tanggung_jawab_jabatan", timSP.getSupervisor().getJabatanTim());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/KertasPemeriksaanPajak.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
        } catch(JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTemplateSuratPelaksanaan(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\TemplateSuratPelaksanaan.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\TemplateSuratPelaksanaan.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            parameter.put("jumlah_rekapitulasi", String.valueOf(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah()));
            
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            parameter.put("nomor_sphp", "973/(nomor_sphp)/BAPENDA");
            parameter.put("tanggal_sphp", "(tanggal_sphp)");
            parameter.put("penandatangan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas());
            
            if((pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null || pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat().equals("")) && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "800/(nomor)/BAPENDA");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", "800/" + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() + "/BAPENDA");
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
            }
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/TemplateSuratPelaksanaan.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPemberitahuanHasilPemeriksaan3(PelaksanaanWrapper pelaksanaanWrapper, 
            TimSP timSP) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPemberitahuanHasilPemeriksaan(3).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPemberitahuanHasilPemeriksaan(3).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil()== null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil().equals(""))){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil());
                Long longTanggalSurat = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil());
                Date tanggalSurat = new Date(longTanggalSurat);
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(tanggalSurat)));
            }
            
            
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            parameter.put("pemberi_sk", pelaksanaanWrapper.getPersiapanWrapper().getPemberiSK());
            parameter.put("penandatangan_jabatan", new ConverterHelper().convertToTitleCaseIteratingChars(pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas()));
            parameter.put("penandatangan_nama", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNamaPegawai());
            parameter.put("penandatangan_pangkat", new ConverterHelper().convertToTitleCaseIteratingChars(pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getPangkat()));
            parameter.put("penandatangan_nip", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNipPegawai());
            parameter.put("jabatan_penandatangan_ttd", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas().toUpperCase());
            
            parameter.put("supervisor_nama", timSP.getSupervisor().getNamaPegawai());
            parameter.put("supervisor_pangkat", timSP.getSupervisor().getPangkat());
            parameter.put("supervisor_nip", timSP.getSupervisor().getNipPegawai());
            
            parameter.put("total_omzet_hasil_pemeriksa", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalOmzetPeriksa()));
            parameter.put("total_pajak_daerah", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPajakPeriksa()));
            parameter.put("total_pajak_yang_telah_disetor", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPajakDisetor()));
            parameter.put("total_pajak_kurang_bayar", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPokokPajak()));
            parameter.put("total_denda", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalDenda()));
            parameter.put("total_pajak_yang_harus_bayar", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah()));
            
            System.out.println("obj : " + pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah());
            System.out.println("money rupiah : " + new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah()));
            
            Long terbilang = pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah().longValue();
            parameter.put("total_pajak", "Rp. " + new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah()) + " (" + new ConverterHelper().angkaToTerbilang(terbilang) + " Rupiah)");
            
            
            
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            parameter.put("nomor_sphp", "973/(nomor_sphp)/BAPENDA");
            parameter.put("tanggal_sphp", "(tanggal_sphp)");
            parameter.put("anggota", new JRBeanCollectionDataSource(timSP.getListAnggota()));
            
            parameter.put("penandatangan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas());
            
            if((pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null || pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat().equals("")) && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "800/(nomor)/BAPENDA");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", "800/" + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() + "/BAPENDA");
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: 
                    parameter.put("jenis_pajak", "Restoran");
                    parameter.put("persen", "10%");
                    break;
                case 1: 
                    parameter.put("jenis_pajak", "Hotel");
                    parameter.put("persen", "10%");
                    break;
                case 2: 
                    parameter.put("jenis_pajak", "Parkir");
                    parameter.put("persen", "25%");
                    break;
            }
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPemberitahuanHasilPemeriksaan(3).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createBeritaAcara8(PelaksanaanWrapper pelaksanaanWrapper, TimSP timSP) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\BeritaAcaraPembahasanAkhirHasilPemeriksaan(8).jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\BeritaAcaraPembahasanAkhirHasilPemeriksaan(8).jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
                        
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            String tanggal = "dd";
            String hari = "EEEE";
            String bulan = "MMMM";
            String tahun = "yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_sphp = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_surat_ba = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_ba = new SimpleDateFormat(tanggal, id);
            SimpleDateFormat df_hari_ba = new SimpleDateFormat(hari, id);
            SimpleDateFormat df_bulan_ba = new SimpleDateFormat(bulan, id);
            SimpleDateFormat df_tahun_ba = new SimpleDateFormat(tahun, id);
            
            parameter.put("nomor_surat", "(nomor surat)");
            
            if(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalBeritaAcara() == null){
                parameter.put("tanggal_surat", "      ");
                parameter.put("bulan_surat", "      ");
                parameter.put("hari_surat", "      ");
                parameter.put("tahun_surat", "      ");
                parameter.put("tanggal_surat_ba", "      ");
            } else {
                Long longTanggalBA = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalBeritaAcara());
                Date tanggalBA = new Date(longTanggalBA);
                
                Long long_tanggal_ba = new Long(String.valueOf(df_tanggal_ba.format(tanggalBA)));
                Long long_tahun_ba = new Long(String.valueOf(df_tahun_ba.format(tanggalBA)));
                
                parameter.put("tanggal_surat", new ConverterHelper().angkaToTerbilang(long_tanggal_ba));
                parameter.put("bulan_surat", String.valueOf(df_bulan_ba.format(tanggalBA)));
                parameter.put("hari_surat", String.valueOf(df_hari_ba.format(tanggalBA)));
                parameter.put("tahun_surat", new ConverterHelper().angkaToTerbilang(long_tahun_ba));
                parameter.put("tanggal_surat_ba", String.valueOf(df_tanggal_surat_ba.format(tanggalBA)));
            }
            
            parameter.put("supervisor_nama", timSP.getSupervisor().getNamaPegawai());
            parameter.put("supervisor_nip", timSP.getSupervisor().getNipPegawai());
            parameter.put("supervisor_jabatan_tim", timSP.getSupervisor().getJabatanTim());
            
            if((pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null || 
                    pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat().equals("")) && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "800/   /BAPENDA");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", "800/" + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() + "/BAPENDA");
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_wajib_pajak", "Restoran");break;
                case 1: parameter.put("jenis_wajib_pajak", "Hotel");break;
                case 2: parameter.put("jenis_wajib_pajak", "Parkir");break;
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "RESTORAN");break;
                case 1: parameter.put("jenis_pajak", "HOTEL");break;
                case 2: parameter.put("jenis_pajak", "PARKIR");break;
            }
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil() == null || 
                    pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil()== null)){
                parameter.put("nomor_sphp", "973/   /BAPENDA");
                parameter.put("tanggal_sphp", "    ");
            } else {
                parameter.put("nomor_sphp", "973/" + pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil()+"/BAPENDA");
                Long longTanggalSurat = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil());
                Date tanggalSurat = new Date(longTanggalSurat);
                parameter.put("tanggal_sphp", String.valueOf(df_tanggal_sphp.format(tanggalSurat)));
            }
            
            parameter.put("penandatangan_jabatan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("penandatangan_nama", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNamaPegawai());
            parameter.put("penandatangan_pangkat", new ConverterHelper().convertToTitleCaseIteratingChars(pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getPangkat()));
            parameter.put("penandatangan_nip", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNipPegawai());
            
            parameter.put("total_omzet_hasil_pemeriksa", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalOmzetPeriksa()));
            parameter.put("total_pajak_daerah", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPajakPeriksa()));
            parameter.put("total_pajak_yang_telah_disetor", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPajakDisetor()));
            parameter.put("total_pajak_kurang_bayar", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalPokokPajak()));
            parameter.put("total_denda", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalDenda()));
            parameter.put("total_pajak_yang_harus_bayar", new ConverterHelper().converterDoubleToMoneyId(pelaksanaanWrapper.getRekapitulasiWrapper().getTotalJumlah()));
            
            parameter.put("anggota_tim", new JRBeanCollectionDataSource(timSP.getListAnggota()));
            parameter.put("anggota_tandatangan_tim_1", new JRBeanCollectionDataSource(timSP.getListAnggota()));
            parameter.put("anggota_tandatangan_tim_2", new JRBeanCollectionDataSource(timSP.getListAnggota()));
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/BeritaAcaraPembahasanAkhirHasilPemeriksaan(8).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createPemberitahuanPemeriksaanPerWP(PersiapanWrapper persiapanWrapper, TimWPWrapper timWPWrapper, NomorTanggalWajibPajakWrapper nomorTanggalWajibPajakWrapper, WajibPajak wajibPajak) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPemberitahuanPemeriksaan.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPemberitahuanPemeriksaan.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
            
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PegawaiService pegawaiService = ServiceFactory.getPegawaiService();
            
             List<Pegawai> anggotaTimList = pegawaiService.getPegawaiByTim(timWPWrapper.getTim().getIdTim());
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(anggotaTimList);
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
//            System.out.println("asdasdasdasdsaadsasd" + persiapanPajakPOJO.getWajibPajak().getNamaWP());
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            if((nomorTanggalWajibPajakWrapper.getNomorPemberitahuanPemeriksaan() == null || nomorTanggalWajibPajakWrapper.getNomorPemberitahuanPemeriksaan().equals("")) && 
                    nomorTanggalWajibPajakWrapper.getTanggalPemberitahuanPemeriksaan() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", nomorTanggalWajibPajakWrapper.getNomorPemberitahuanPemeriksaan());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(nomorTanggalWajibPajakWrapper.getTanggalPemberitahuanPemeriksaan())));
            }
            
            
            parameter.put("wajib_pajak", wajibPajak);
            
            if((persiapanWrapper.getNomorSurat() == null || persiapanWrapper.getNomorSurat().equals("")) && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            parameter.put("jabatan_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getJabatanDinas()));
            parameter.put("pangkat_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getPangkat()));
            parameter.put("jabatan_penandatangan_ttd", persiapanWrapper.getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("nama_penandatangan", persiapanWrapper.getPenandatangan().getNamaPegawai().toUpperCase());
            
            parameter.put("hari", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            switch(wajibPajak.getJenisWp()){
                case 0: parameter.put("jenis_wp", "Restoran");break;
                case 1: parameter.put("jenis_wp", "Hotel");break;
            }
            
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun());
            parameter.put("tim", timWPWrapper.getTim().getNamaTim());
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            parameter.put("anggota_tim", new JRBeanCollectionDataSource(anggotaTimList));
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/ReportPemberitahuanPemeriksaanRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createPersiapanPeminjamanBukuPerWP(PersiapanWrapper persiapanWrapper, TimWPWrapper timWPWrapper, NomorTanggalWajibPajakWrapper nomorTanggalWajibPajakWrapper, WajibPajak wajibPajak) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPeminjamanBuku.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\ReportPeminjamanBuku.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
            
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String masaAwal =  converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun();
            String masaAkhir = converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun();
            
            
            if(wajibPajak.getListPinjaman().isEmpty()){
                ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(wajibPajak, masaAwal, masaAkhir);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(wajibPajak.getListPinjaman());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            if((nomorTanggalWajibPajakWrapper.getNomorPeminjamanDokumen()== null || nomorTanggalWajibPajakWrapper.getNomorPeminjamanDokumen().equals("")) && 
                    nomorTanggalWajibPajakWrapper.getTanggalPeminjamanDokumen()== null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", nomorTanggalWajibPajakWrapper.getNomorPeminjamanDokumen());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(nomorTanggalWajibPajakWrapper.getTanggalPeminjamanDokumen())));
            }
            
            
            parameter.put("wajib_pajak", wajibPajak);
            
            if((persiapanWrapper.getNomorSurat() == null || persiapanWrapper.getNomorSurat().equals("")) && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            
            parameter.put("buku_pinjaman", beanColDataSource);
            
            parameter.put("jabatan_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getJabatanDinas()));
            parameter.put("pangkat_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getPangkat()));
            parameter.put("jabatan_penandatangan_ttd", persiapanWrapper.getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("nama_penandatangan", persiapanWrapper.getPenandatangan().getNamaPegawai().toUpperCase());
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/ReportPeminjamanBuku.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createPersiapanDokumenPinjamanPerWP(WP wp, WajibPajak wajibPajak, PersiapanWrapper persiapanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\DaftarBukuPinjaman.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\DaftarBukuPinjaman.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            String masaAwal = converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun();
            String masaAkhir = converterHelper.convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAkhirbulan()) + " " +
                               persiapanWrapper.getMasaPajakAkhirTahun();
            if(wajibPajak.getListPinjaman().size() == 0)
                ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(
                    wajibPajak, masaAwal, masaAkhir);

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(wajibPajak.getListPinjaman());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            System.out.println("asdasdasdasdsaadsasd" + wajibPajak.getListPinjaman().size());
            
            parameter.put("wajib_pajak", wajibPajak);
            
            
            parameter.put("buku_peminjaman", beanColDataSource);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter, 
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/DaftarBukuPinjamanRestoran.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createSuratPerintahBaru() {
        try {
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
            
            //data dummi
            PersiapanWrapperJasper dummi = new PersiapanWrapperJasper();

            dummi.setNomorSurat(persiapanWrapper.getNomorSurat());
            dummi.setTanggalPengesahan(persiapanWrapper.getTanggalPengesahan());
            dummi.setDasarNomor(persiapanWrapper.getDasarNomor());
            dummi.setDasarTanggal(persiapanWrapper.getDasarTanggal());
            dummi.setDasarTahunAnggaran(persiapanWrapper.getDasarTahunAnggaran());
            dummi.setNama(persiapanWrapper.getNama());
            dummi.setJabatan(persiapanWrapper.getJabatan());

            dummi.setMasaPajakAwalBulan(persiapanWrapper.getMasaPajakAwalBulan());
            dummi.setMasaPajakAwalTahun(persiapanWrapper.getMasaPajakAwalTahun());
            dummi.setMasaPajakAkhirbulan(persiapanWrapper.getMasaPajakAkhirbulan());
            dummi.setMasaPajakAkhirTahun(persiapanWrapper.getMasaPajakAkhirTahun());
            dummi.setTahapKe(persiapanWrapper.getTahapKe());

            dummi.setLamaPelaksanaan(persiapanWrapper.getLamaPelaksanaan());
            dummi.setBiayaNomorAPBD(persiapanWrapper.getBiayaNomorAPBD());
            dummi.setBiayaTahunAPBD(persiapanWrapper.getBiayaTahunAPBD());
            dummi.setBiayaTanggalAPBD(persiapanWrapper.getBiayaTanggalAPBD());

            dummi.setDitetapkanDi(persiapanWrapper.getDitetapkanDi());

            dummi.setPenandatangan(persiapanWrapper.getPenandatangan());

            PegawaiService pegawaiService = ServiceFactory.getPegawaiService();

            for (TimWPWrapper tim : persiapanWrapper.getTimWPWrappers()) {

                List<Pegawai> anggotaTimList = pegawaiService.getPegawaiByTim(tim.getTim().getIdTim());
                List<AnggotaDanWajibPajakWrapper> wajibPajakList = new ArrayList<AnggotaDanWajibPajakWrapper>();

                int jumlah = anggotaTimList.size();
                if(jumlah < tim.getWajibPajaks().size()){
                    jumlah = tim.getWajibPajaks().size();
                }
                
                for (int i = 0; i < jumlah; i++) {
                    AnggotaDanWajibPajakWrapper wp = new AnggotaDanWajibPajakWrapper();

                    if(i < tim.getWajibPajaks().size()){
                        wp.setIdWajibPajak(tim.getWajibPajaks().get(i).getNpwpd());
                        wp.setNamaWajibPajak(tim.getWajibPajaks().get(i).getNamaWajibPajak());
                        wp.setJenisWp(tim.getWajibPajaks().get(i).getJenisWp());
                    } else {
                        wp.setIdWajibPajak("");
                        wp.setNamaWajibPajak("");
                        wp.setJenisWp((short) -1);
                    }

                    if (i < anggotaTimList.size()) {
                        wp.setIdTim(anggotaTimList.get(i).getIdTim());
                        wp.setNipPegawai(anggotaTimList.get(i).getNipPegawai());
                        wp.setNamaPegawai(anggotaTimList.get(i).getNamaPegawai());
                        wp.setPangkat(anggotaTimList.get(i).getPangkat());
                        wp.setGolongan(anggotaTimList.get(i).getGolongan());
                        wp.setJabatanTim(anggotaTimList.get(i).getJabatanTim());
                    } else {
                        wp.setIdTim("");
                        wp.setNipPegawai("");
                        wp.setNamaPegawai("");
                        wp.setPangkat("");
                        wp.setGolongan("");
                    }

                    wajibPajakList.add(wp);
                }
                
                for (AnggotaDanWajibPajakWrapper wajibPajakWrapper : wajibPajakList){
                    System.out.println("test wp : "+wajibPajakWrapper.getNamaPegawai());
                }

                TimWPWrapperJasper objTimWPWrapper
                        = new TimWPWrapperJasper(
                                tim.getPenanggungJawab().getNipPegawai(),
                                tim.getPenanggungJawab().getNamaPegawai(),
                                tim.getPenanggungJawab().getPangkat(),
                                tim.getPenanggungJawab().getGolongan(),
                                tim.getPenanggungJawab().getJabatanTim(),

                                tim.getSupervisor().getNipPegawai(),
                                tim.getSupervisor().getNamaPegawai(),
                                tim.getSupervisor().getPangkat(),
                                tim.getSupervisor().getGolongan(),
                                tim.getSupervisor().getJabatanTim(),

                                tim.getTim().getNamaTim(),
                                wajibPajakList
                        );
                JRBeanCollectionDataSource beanColDataSourceWp =
                        new JRBeanCollectionDataSource(wajibPajakList);
                objTimWPWrapper.setWajibPajakJasper(beanColDataSourceWp);
                objTimWPWrapper.setListWP(tim.getWajibPajaks());

                dummi.getTimWPWrapperJaspers().add(objTimWPWrapper);

            }

            //data dummi
            
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                System.out.print("test"); 
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPerintah.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratPerintah.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (TimWPWrapperJasper obj : dummi.getTimWPWrapperJaspers()) {
                System.out.println(obj.getNamaPegawaiPenanggungJawab());
                System.out.println(obj.getNamaPegawaiSupervisor());
                System.out.println(obj.getNamaTim());
            }

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dummi.getTimWPWrapperJaspers());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_pengesahan = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_dasar_tanggal = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_biaya_tanggal_apbd = new SimpleDateFormat(pattern, id);
            
            if((persiapanWrapper.getNomorSurat() == null || persiapanWrapper.getNomorSurat().equals("")) && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_pengesahan", "    ");
            } else {
                parameter.put("nomor_surat", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_pengesahan", String.valueOf(df_tanggal_pengesahan.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("pemberi_sk", persiapanWrapper.getPemberiSK());
            parameter.put("dasar_nomor", persiapanWrapper.getDasarNomor());
            parameter.put("dasar_tanggal", String.valueOf(df_dasar_tanggal.format(persiapanWrapper.getDasarTanggal())));
            parameter.put("dasar_tahun_anggaran", persiapanWrapper.getDasarTahunAnggaran());
            parameter.put("nama_perintah", persiapanWrapper.getNama());
            parameter.put("jabatan_perintah", persiapanWrapper.getJabatan());
            parameter.put("masa_pajak_awal", converterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAwalBulan())
                    + " " + String.valueOf(persiapanWrapper.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", converterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAkhirbulan())
                    + " " + String.valueOf(persiapanWrapper.getMasaPajakAkhirTahun()));
            parameter.put("tahap_ke", new ConverterHelper().toRoman(persiapanWrapper.getTahapKe()));
            parameter.put("lama_pelaksanaan", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            parameter.put("lama_pelaksanaan_terbilang", "( " + new ConverterHelper().angkaToTerbilang(Long.valueOf(persiapanWrapper.getLamaPelaksanaan())) + " )");
            parameter.put("biaya_tahun_apbd", String.valueOf(persiapanWrapper.getBiayaTahunAPBD()));
            parameter.put("biaya_nomor_apbd", persiapanWrapper.getBiayaNomorAPBD());
            parameter.put("biaya_tanggal_apbd", String.valueOf(df_biaya_tanggal_apbd.format(persiapanWrapper.getBiayaTanggalAPBD())));
            parameter.put("ditetapkan_di", persiapanWrapper.getDitetapkanDi());
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            parameter.put("jabatan_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getJabatanDinas()));
            parameter.put("pangkat_penandatangan", converterHelper.convertToTitleCaseIteratingChars(persiapanWrapper.getPenandatangan().getPangkat()));
            parameter.put("jabatan_penandatangan_ttd", persiapanWrapper.getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("nama_penandatangan", persiapanWrapper.getPenandatangan().getNamaPegawai().toUpperCase());
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);

            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPerintah.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (Exception ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createSuratTeguran1(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratTeguran1.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratTeguran1.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(pelaksanaanWrapper.getPersiapanWrapper().getNomorTanggalWPList());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_pb = new SimpleDateFormat(pattern, id);
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1() == null)){
                parameter.put("nomor_tp", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                Long longTanggalT1 = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1());
                Date tanggalT1 = new Date(longTanggalT1);
                
                parameter.put("nomor_tp", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(tanggalT1)));
            }
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratPeminjaman() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratPeminjaman().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratPeminjaman()== null)){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_pb", "    ");
            } else {
                Long longTanggalT1 = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratPeminjaman());
                Date tanggalT1 = new Date(longTanggalT1);
                
                parameter.put("nomor_surat", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratPeminjaman());
                parameter.put("tanggal_pb", String.valueOf(df_tanggal_pb.format(tanggalT1)));
            }
            
            parameter.put("wajib_pajak", pelaksanaanWrapper.getWpSelected());
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            
            parameter.put("penandatangan_jabatan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("penandatangan_nama", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNamaPegawai());
            parameter.put("penandatangan_pangkat", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getPangkat());
            parameter.put("penandatangan_nip", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNipPegawai());
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
                case 2: parameter.put("jenis_pajak", "Parkir");break;
            }
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
           
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter,
                       beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratTeguran1.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void createSuratTeguran2(PelaksanaanWrapper pelaksanaanWrapper)   {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratTeguran2.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\SuratTeguran2.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(pelaksanaanWrapper.getPersiapanWrapper().getNomorTanggalWPList());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_surat = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_pb = new SimpleDateFormat(pattern, id);
            SimpleDateFormat df_tanggal_tp = new SimpleDateFormat(pattern, id);
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran2()== null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran2().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran2()== null)){
                parameter.put("nomor", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                Long longTanggalT1 = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran2());
                Date tanggalT1 = new Date(longTanggalT1);
                
                parameter.put("nomor", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran2());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_tp.format(tanggalT1)));
            }
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1() == null)){
                parameter.put("nomor_tp", "973/   /BAPENDA");
                parameter.put("tanggal_tp", "    ");
            } else {
                Long longTanggalT1 = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1());
                Date tanggalT1 = new Date(longTanggalT1);
                
                parameter.put("nomor_tp","973/" + pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1() + "/Bapenda");
                parameter.put("tanggal_tp", String.valueOf(df_tanggal_tp.format(tanggalT1)));
            }
            
            if((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratPeminjaman() == null || pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratPeminjaman().equals("")) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratPeminjaman()== null)){
                parameter.put("nomor_surat", "       ");
                parameter.put("tanggal_pb", "       ");
            } else {
                Long longTanggalT1 = new Long(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratPeminjaman());
                Date tanggalT1 = new Date(longTanggalT1);
                
                parameter.put("nomor_surat",pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratPeminjaman());
                parameter.put("tanggal_pb", String.valueOf(df_tanggal_pb.format(tanggalT1)));
            }
            
            if ((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getHariTeguran2() == null) && 
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getJamTeguran2() == null)&&
                    (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTempatTeguran2() == null)) {
                parameter.put("hari", "   ");
                parameter.put("waktu", "   ");
                parameter.put("lokasi", "   ");
            } else {
                parameter.put("hari", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getHariTeguran2());
                parameter.put("waktu", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getJamTeguran2());
                parameter.put("lokasi", pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTempatTeguran2());
            }
            
            
            parameter.put("wajib_pajak", pelaksanaanWrapper.getWpSelected());
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            
            parameter.put("penandatangan_jabatan", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getJabatanDinas().toUpperCase());
            parameter.put("penandatangan_nama", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNamaPegawai());
            parameter.put("penandatangan_pangkat", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getPangkat());
            parameter.put("penandatangan_nip", pelaksanaanWrapper.getPersiapanWrapper().getPenandatangan().getNipPegawai());
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
                case 2: parameter.put("jenis_pajak", "Parkir");break;
            }
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
           
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratTeguran2.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createCoverTemplate1(PelaksanaanWrapper pelaksanaanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\CoverTemplate1.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\CoverTemplate1.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(pelaksanaanWrapper.getPersiapanWrapper().getNomorTanggalWPList());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wp", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd", pelaksanaanWrapper.getWpSelected().getNpwpd());
            
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Bulan Awal : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + converterHelper.convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
            System.out.println("Tahun Akhir : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPenyetaanKesanggupanMembayarPajakKurangBarang(6).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createCoverTemplate2(PelaporanWrapper pelaporanWrapper) {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\CoverTemplate2.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\CoverTemplate2.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                              
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<String> text = new ArrayList<>();
            text.add("dummi");
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(text);
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            Locale id = new Locale("in", "ID");
            String pattern = "dd MMMM yyyy";
            
            SimpleDateFormat df_tanggal_sp = new SimpleDateFormat(pattern, id);
            
            parameter.put("nama_wp", pelaporanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd", pelaporanWrapper.getWpSelected().getNpwpd());
            
            int bulanAwal = new Date(Long.valueOf(pelaporanWrapper.getSuratPerintahSelected().getMasaPajakAwal()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue()-1;
            
            int tahunAwal = new Date(Long.valueOf(pelaporanWrapper.getSuratPerintahSelected().getMasaPajakAwal()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getYear();
            
            int bulanAkhir = new Date(Long.valueOf(pelaporanWrapper.getSuratPerintahSelected().getMasaPajakAkhir()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getMonthValue()-1;
            
            int tahunAkhir = new Date(Long.valueOf(pelaporanWrapper.getSuratPerintahSelected().getMasaPajakAkhir()))
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getYear();
            
            parameter.put("pajak_awal", converterHelper.convertBulanIntegerIntoString(bulanAwal) + " " +
                               String.valueOf(tahunAwal));
            parameter.put("pajak_akhir", converterHelper.convertBulanIntegerIntoString(bulanAkhir) + " " +
                               String.valueOf(tahunAkhir));
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/SuratPenyetaanKesanggupanMembayarPajakKurangBarang(6).pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createQuesionerHotel() {
        try {
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
            
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\QuesionerHotel.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\QuesionerHotel.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(persiapanWrapper.getNomorTanggalWPList());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/QuesionerHotel.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createQuesionerParkir() {
        try {
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
            
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            String logoFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\QuesionerParkir.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\QuesionerParkir.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(persiapanWrapper.getNomorTanggalWPList());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            String rootLogo = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            logoFile = rootLogo.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "images\\logo_kab_bekasi.png");
            parameter.put("logo", logoFile);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/QuesionerParkir.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createLaporanEvaluasi(PelaporanWrapper pelaporanWrapper) {
        List<SPColumnPelaporan> sPColumnPelaporans = (List<SPColumnPelaporan>) SessionProvider.getGlobalSessionsMap().get("evaluasi_wrapper");
        Integer tahunAnggaranSK = (Integer) SessionProvider.getGlobalSessionsMap().get("tahun_anggaran");
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\LaporanEvaluasi.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\LaporanEvaluasi.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            parameter.put("tahun_anggaran", String.valueOf(tahunAnggaranSK));
            parameter.put("list_sp", new JRBeanCollectionDataSource(sPColumnPelaporans));
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    new JRBeanCollectionDataSource(new ArrayList<>(Arrays.asList("abc"))));
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/QuesionerParkir.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void testGamber() {
        try {
            String jasperPathFile = null;
            String jrxmlPathFile = null;
            
            try {
                String root = new File(ReportServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                jasperPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\gambar.jasper");
                jasperPathFile = "file:///" + jasperPathFile;
                jrxmlPathFile = root.replace("target\\pajak-simulator-1.0-SNAPSHOT.jar", "jasper\\gambar.jrxml");
                System.out.println("jasper path : " + jasperPathFile);
                System.out.println("jrxml path : " + jrxmlPathFile);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            System.out.println("masuk service");
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<String> text = new ArrayList<>();
            text.add("dummi");
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(text);
            
            String jasperLogoPath = "./logo_kab_bekasi.png";
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            parameter.put("logo", this.getClass().getResourceAsStream(jasperLogoPath));
            parameter.put("list", beanColDataSource);
            
            try {
               JasperFillManager.fillReportToFile(
               jasperPathFile, parameter, beanColDataSource);
            } catch (JRException e) {
                System.out.println("JRException ex");
               e.printStackTrace();
            }
            
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(
                    report, 
                    parameter,
                    beanColDataSource);
            
            try {
                File file = new File("C:/Users/Bayu Arafli/Documents/NetBeansProjects/pajak-simulator/pdf/QuesionerParkir.pdf");
                File parent = file.getParentFile();
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                
                OutputStream output = new FileOutputStream(file);
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            System.out.println("JRException ex");
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}