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
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapperJasper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            
            parameter.put("pajak_awal", convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", convertBulanIntegerIntoString(
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
            
            parameter.put("pajak_awal", convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", convertBulanIntegerIntoString(
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

            String masaAwal = convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun();
            String masaAkhir = convertBulanIntegerIntoString(
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
            DateFormat df_tanggal_pengesahan = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_dasar_tanggal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_biaya_tanggal_apbd = new SimpleDateFormat("dd MMMM yyyy");

            if(persiapanWrapper.getNomorSurat() == null && persiapanWrapper.getTanggalPengesahan() == null){
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
            parameter.put("masa_pajak_awal", convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAwalBulan())
                    + " " + String.valueOf(persiapanWrapper.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAkhirbulan())
                    + " " + String.valueOf(persiapanWrapper.getMasaPajakAkhirTahun()));
            parameter.put("tahap_ke", String.valueOf(persiapanWrapper.getTahapKe()));
            parameter.put("lama_pelaksanaan", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            parameter.put("biaya_tahun_apbd", String.valueOf(persiapanWrapper.getBiayaTahunAPBD()));
            parameter.put("biaya_nomor_apbd", persiapanWrapper.getBiayaNomorAPBD());
            parameter.put("biaya_tanggal_apbd", String.valueOf(df_biaya_tanggal_apbd.format(persiapanWrapper.getBiayaTanggalAPBD())));
            parameter.put("ditetapkan_di", persiapanWrapper.getDitetapkanDi());
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());

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

//            Pegawai penanggungJawab = new Pegawai();
//            penanggungJawab.setIdTim("");
//            penanggungJawab.setNamaPegawai("Bagus");
//            penanggungJawab.setNipPegawai("69696969");
//            penanggungJawab.setGolongan("Java / V.a");
//            penanggungJawab.setJabatanTim("Senior");
//
//            Pegawai supervisor = new Pegawai();
//            supervisor.setIdTim("");
//            supervisor.setNamaPegawai("Sra");
//            supervisor.setNipPegawai("121212");
//            supervisor.setGolongan("Angular / V.a");
//            supervisor.setJabatanTim("Senior Angular");

//            Tim tim = new Tim();
//            tim.setIdTim("1");
//            tim.setNamaTim("Tim 1");


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
            DateFormat df_tanggal_pengesahan = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_dasar_tanggal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_biaya_tanggal_apbd = new SimpleDateFormat("dd MMMM yyyy");

            if(dummi.getNomorSurat() == null && dummi.getTanggalPengesahan() == null){
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
            parameter.put("masa_pajak_awal", convertBulanIntegerIntoString(dummi.getMasaPajakAwalBulan())
                    + " " + String.valueOf(dummi.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", convertBulanIntegerIntoString(dummi.getMasaPajakAkhirbulan())
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

//            for(TimWPWrapperJasper timWP : dummi.getTimWPWrapperJaspers()){
//                for(WajibPajak wp : timWP.getListWP()){
//                    System.out.println("Masuk wp " + wp.getNamaWajibPajak());
//                    createPersiapanPajakRestoranReport(
//                        dummi, wp);
//                    createPersiapanPajakRestoranReport1(
//                            dummi, wp, timWP);
//                    switch(wp.getJenisWp()){
//                        case 0: createPersiapanDokumenPinjaman(WP.RESTORAN, wp, dummi);
//                            createTandaTerima(WP.RESTORAN, wp, dummi);
//                            createQuesionerRestoran(WP.RESTORAN, wp, dummi);
//                            break;
//                        case 1: createPersiapanDokumenPinjaman(WP.HOTEL, wp, dummi);
//                            createTandaTerima(WP.HOTEL, wp, dummi);
//                            createQuesionerRestoran(WP.HOTEL, wp, dummi);
//                            break;
//                    }
//                }
//            }

//            ConverterHelper sp = new ConverterHelper();
//            ServiceFactory.getSuratPerintahService().createSuratPerintah(sp.convertPersiapanWrapperIntoSuratPerintah(
//                    persiapanWrapper
//            ));
//            System.out.println("Masuk " + sp.convertPersiapanWrapperIntoSuratPerintah(
//                    persiapanWrapper
//            ).getListTim().size());
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
    
    private String convertJenisWPShortIntoString(short jenisWp) {
        switch(jenisWp) {
            case 0: return "Restoran";
            case 1: return "Hotel";
            case 2: return "Parkir";
            case 3: return "Hiburan";
            case 4: return "Penerangan Jalan";
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
            DateFormat df_tanggal_surat = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_tanggal_sp = new SimpleDateFormat("dd MMMM yyyy");
            
            if(persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPeminjamanDokumen() == null && 
                    persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPeminjamanDokumen() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPeminjamanDokumen());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPeminjamanDokumen())));
            }
            

            if(persiapanWrapper.getNomorSurat() == null && persiapanWrapper.getTanggalPengesahan() == null){
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
            DateFormat df_tanggal_surat = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_tanggal_sp = new SimpleDateFormat("dd MMMM yyyy");
            
            if(persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPemberitahuanPemeriksaan() == null && 
                    persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPemberitahuanPemeriksaan() == null){
                parameter.put("nomor_surat", "   ");
                parameter.put("tanggal_surat", "    ");
            } else {
                parameter.put("nomor_surat", persiapanWrapper.getNomorTanggalWPList().get(index).getNomorPemberitahuanPemeriksaan());
                parameter.put("tanggal_surat", String.valueOf(df_tanggal_surat.format(persiapanWrapper.getNomorTanggalWPList().get(index).getTanggalPemberitahuanPemeriksaan())));
            }
            
            
            parameter.put("wajib_pajak", wp);
            
            if(persiapanWrapper.getNomorSurat() == null && persiapanWrapper.getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", persiapanWrapper.getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(persiapanWrapper.getTanggalPengesahan())));
            }
            
            parameter.put("penandatangan", persiapanWrapper.getPenandatangan());
            parameter.put("hari", String.valueOf(persiapanWrapper.getLamaPelaksanaan()));
            switch(wp.getJenisWp()){
                case 0: parameter.put("jenis_wp", "Restoran");break;
                case 1: parameter.put("jenis_wp", "Hotel");break;
            }
            
            parameter.put("pajak_awal", convertBulanIntegerIntoString(
                               persiapanWrapper.getMasaPajakAwalBulan()) + " " +
                               persiapanWrapper.getMasaPajakAwalTahun());
            parameter.put("pajak_akhir", convertBulanIntegerIntoString(
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
            
            DateFormat df_tanggal_sp = new SimpleDateFormat("dd MMMM yyyy");
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            if(pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
            }
            
            parameter.put("masa_pajak_awal", convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
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
            
            DateFormat df_tanggal_sp = new SimpleDateFormat("dd MMMM yyyy");
            
            parameter.put("nama_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            parameter.put("npwpd_wajib_pajak", pelaksanaanWrapper.getWpSelected().getNpwpd());
            parameter.put("alamat_wajib_pajak", pelaksanaanWrapper.getWpSelected().getJalan());
            
            if(pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat() == null && pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan() == null){
                parameter.put("nomor_sp", "   ");
                parameter.put("tanggal_sp", "    ");
            } else {
                parameter.put("nomor_sp", pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
                parameter.put("tanggal_sp", String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            }
            
            switch(pelaksanaanWrapper.getWpSelected().getJenisWp()){
                case 0: parameter.put("jenis_pajak", "Restoran");break;
                case 1: parameter.put("jenis_pajak", "Hotel");break;
            }
            
            parameter.put("masa_pajak_awal", convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            parameter.put("masa_pajak_akhir", convertBulanIntegerIntoString(
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()) + " " +
                               pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun());
            
            System.out.println("Nama Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
            System.out.println("NPWPD Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getNpwpd());
            System.out.println("Alamat Wajib Pajak : " + pelaksanaanWrapper.getWpSelected().getJalan());
            System.out.println("Nomor SP : " + pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat());
            System.out.println("Tanggal SP : " + String.valueOf(df_tanggal_sp.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan())));
            System.out.println("Jenis Pajak : " + pelaksanaanWrapper.getWpSelected().getJenisWp());
            System.out.println("Bulan Awal : " + convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()));
            System.out.println("Tahun Awal : " + pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun());
            System.out.println("Bulan Akhir : " + convertBulanIntegerIntoString(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()));
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
        }
    }
    
}
