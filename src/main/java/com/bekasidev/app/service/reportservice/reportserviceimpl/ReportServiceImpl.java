/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.service.reportservice.reportserviceimpl;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
import com.bekasidev.app.viewfx.javafxapplication.model.AnggotaDanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapperJasper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapperJasper;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
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
    public void createPersiapanPajakRestoranReport() {
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
                File file = new File("C:/Users/Bayu Arafli/Documents/v1/pajak-simulator-v1/pdf/restoran/ReportPeminjamanBukuRestoran.pdf");
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
    public void createPersiapanPajakRestoranReport1() {
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
                File file = new File("C:/Users/Bayu Arafli/Documents/v1/pajak-simulator-v1/pdf/restoran/ReportPemberitahuanPemeriksaanRestoran.pdf");
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
    public void createPersiapanDokumenPinjaman() {
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
                        .get("persiapan_pajak_restoran");

            BerkasPersiapan bp = new BerkasPersiapan();
            bp.setMasaPajakAwal(persiapanPajakPOJO.getMasaPajakBulanAwal() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAwal());
            bp.setMasaPajakAkhir(persiapanPajakPOJO.getMasaPajakBulanAkhir() 
                    + " " + persiapanPajakPOJO.getMasaPajakTahunAkhir());
            ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(bp, WP.RESTORAN);

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(bp.getListPinjaman());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            System.out.println("asdasdasdasdsaadsasd" + bp.getListPinjaman().size());
            
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
                File file = new File("C:/Users/Bayu Arafli/Documents/v1/pajak-simulator-v1/pdf/restoran/DaftarBukuPinjamanRestoran.pdf");
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
                File file = new File("C:/Users/Bayu Arafli/Documents/v1/pajak-simulator-v1/pdf/hotel/ReportPeminjamanBukuHotel.pdf");
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
                File file = new File("C:/Users/Bayu Arafli/Documents/v1/pajak-simulator-v1/pdf/hotel/ReportPemberitahuanPemeriksaanHotel.pdf");
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
            ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(bp, WP.HOTEL);

            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(bp.getListPinjaman());

            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            
            System.out.println("asdasdasdasdsaadsasd" + bp.getListPinjaman().size());
            
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
                File file = new File("C:/Users/Bayu Arafli/Documents/v1/pajak-simulator-v1/pdf/hotel/DaftarBukuPinjamanHotel.pdf");
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
            //data dummi
            PersiapanWrapper dummi = new PersiapanWrapper();
        
            dummi.setNomorSurat("505");
            dummi.setTanggalPengesahan(new Date());
            dummi.setDasarNomor("254/1564/Bugpabn");
            dummi.setDasarTanggal(new Date());
            dummi.setDasarTahunAnggaran("2017");
            dummi.setNama("Bayu Arafli");
            dummi.setJabatan("Pelajar");
            
            dummi.setMasaPajakAwalBulan(0);
            dummi.setMasaPajakAwalTahun(2017);
            dummi.setMasaPajakAkhirbulan(11);
            dummi.setMasaPajakAkhirTahun(2017);
            dummi.setTahapKe(1);
            
            dummi.setLamaPelaksanaan(10);
            dummi.setBiayaNomorAPBD("214/145/13544");
            dummi.setBiayaTahunAPBD(2017);
            dummi.setBiayaTanggalAPBD(new Date());
            
            dummi.setDitetapkanDi("Cimahi");
            
            Pegawai penandaTanggan = new Pegawai();
            penandaTanggan.setIdTim("");
            penandaTanggan.setNamaPegawai("Rony");
            penandaTanggan.setNipPegawai("123456789");
            penandaTanggan.setGolongan("Expert / VI.a");
            penandaTanggan.setJabatan("Senior Group");
            
            dummi.setPenandatangan(penandaTanggan);
            
            Pegawai penanggungJawab = new Pegawai();
            penanggungJawab.setIdTim("");
            penanggungJawab.setNamaPegawai("Bagus");
            penanggungJawab.setNipPegawai("69696969");
            penanggungJawab.setGolongan("Java / V.a");
            penanggungJawab.setJabatan("Senior");
            
            Pegawai supervisor = new Pegawai();
            supervisor.setIdTim("");
            supervisor.setNamaPegawai("Sra");
            supervisor.setNipPegawai("121212");
            supervisor.setGolongan("Angular / V.a");
            supervisor.setJabatan("Senior Angular");
            
            Tim tim = new Tim();
            tim.setIdTim("1");
            tim.setNamaTim("Tim 1");
            
            WajibPajak wp = new WajibPajak();
            wp.setIdWajibPajak("wp1");
            wp.setNamaWajibPajak("KALEYOS");
            wp.setJenisWp((short) 0);
            wp.setJalan("Karang Asih II");
            wp.setKecamatan("Karang Bunga");
            wp.setDesa("Bunga");
            wp.setNamaPemilik("Ucok");
            wp.setTelepon("0812345678");
            wp.setFax("08254684");
            wp.setTahunMulaiOperasional("2015");
            
            List<WajibPajak> wajibPajak = new ArrayList<WajibPajak>();
            wajibPajak.add(wp);
            
//            dummi.getTimWPWrappers().add(new TimWPWrapper(
//                    penanggungJawab,
//                    supervisor,
//                    tim,
//                    wajibPajak
//            ));
//            dummi.getTimWPWrappers().add(new TimWPWrapper(
//                    penanggungJawab,
//                    supervisor,
//                    tim,
//                    wajibPajak
//            ));
            
            
            
            
            //data dummi
            
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://SuratPerintah.jasper";
            String jrxmlPathFile = "D://SuratPerintah.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_wrapper");
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dummi.getTimWPWrappers());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            DateFormat df_tanggal_pengesahan = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_dasar_tanggal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_biaya_tanggal_apbd = new SimpleDateFormat("dd MMMM yyyy");

            parameter.put("nomor_surat", dummi.getNomorSurat());
            parameter.put("tanggal_pengesahan", String.valueOf(df_tanggal_pengesahan.format(dummi.getTanggalPengesahan())));
            
            parameter.put("dasar_nomor", dummi.getDasarNomor());
            parameter.put("dasar_tanggal", String.valueOf(df_dasar_tanggal.format(dummi.getDasarTanggal())));
            parameter.put("dasar_tahun_anggaran", dummi.getDasarTahunAnggaran());
            parameter.put("nama_perintah", dummi.getNama());
            parameter.put("jabatan_perintah", dummi.getJabatan());
            parameter.put("masa_pajak_awal", String.valueOf(dummi.getMasaPajakAwalBulan())
                    + " " + String.valueOf(dummi.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", String.valueOf(dummi.getMasaPajakAkhirbulan())
                    + " " + String.valueOf(dummi.getMasaPajakAkhirTahun()));
            parameter.put("tahap_ke", String.valueOf(dummi.getTahapKe()));
            parameter.put("lama_pelaksanaan", String.valueOf(dummi.getLamaPelaksanaan()));
            parameter.put("biaya_tahun_apbd", String.valueOf(dummi.getBiayaTahunAPBD()));
            parameter.put("biaya_nomor_apbd", dummi.getBiayaNomorAPBD());
            parameter.put("biaya_tanggal_apbd", String.valueOf(df_biaya_tanggal_apbd.format(dummi.getBiayaTanggalAPBD())));
            parameter.put("ditetapkan_di", dummi.getDitetapkanDi());
            parameter.put("penandatangan", dummi.getPenandatangan());

//            parameter.put("nomor_surat", "jd");
//            parameter.put("tanggal_pengesahan", "ds");
//            
//            parameter.put("dasar_nomor", "ss");
//            parameter.put("dasar_tanggal", "ss");
//            parameter.put("dasar_tahun_anggaran", "ss");
//            parameter.put("nama_perintah", "ss");
//            parameter.put("jabatan_perintah", "ss");
//            parameter.put("masa_pajak_awal", "ss");
//            parameter.put("masa_pajak_akhir", "ss");
//            parameter.put("tahap_ke", "ss");
//            parameter.put("lama_pelaksanaan", "ss");
//            parameter.put("biaya_tahun_apbed", "ss");
//            parameter.put("biaya_nomor_apbed", "ss");
//            parameter.put("biaya_tanggal_apbed", "ss");
//            parameter.put("ditetapkan_di", "ss");
//            parameter.put("penandatangan", dummi.getPenandatangan());
            
            
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
            //data dummi
            PersiapanWrapperJasper dummi = new PersiapanWrapperJasper();
        
            dummi.setNomorSurat("505");
            dummi.setTanggalPengesahan(new Date());
            dummi.setDasarNomor("254/1564/Bugpabn");
            dummi.setDasarTanggal(new Date());
            dummi.setDasarTahunAnggaran("2017");
            dummi.setNama("Bayu Arafli");
            dummi.setJabatan("Pelajar");
            
            dummi.setMasaPajakAwalBulan(0);
            dummi.setMasaPajakAwalTahun(2017);
            dummi.setMasaPajakAkhirbulan(11);
            dummi.setMasaPajakAkhirTahun(2017);
            dummi.setTahapKe(1);
            
            dummi.setLamaPelaksanaan(10);
            dummi.setBiayaNomorAPBD("214/145/13544");
            dummi.setBiayaTahunAPBD(2017);
            dummi.setBiayaTanggalAPBD(new Date());
            
            dummi.setDitetapkanDi("Cimahi");
            
            Pegawai penandaTanggan = new Pegawai();
            penandaTanggan.setIdTim("");
            penandaTanggan.setNamaPegawai("Rony");
            penandaTanggan.setNipPegawai("123456789");
            penandaTanggan.setGolongan("Expert / VI.a");
            penandaTanggan.setJabatan("Senior Group");
            
            dummi.setPenandatangan(penandaTanggan);
            
//            Pegawai penanggungJawab = new Pegawai();
//            penanggungJawab.setIdTim("");
//            penanggungJawab.setNamaPegawai("Bagus");
//            penanggungJawab.setNipPegawai("69696969");
//            penanggungJawab.setGolongan("Java / V.a");
//            penanggungJawab.setJabatan("Senior");
//            
//            Pegawai supervisor = new Pegawai();
//            supervisor.setIdTim("");
//            supervisor.setNamaPegawai("Sra");
//            supervisor.setNipPegawai("121212");
//            supervisor.setGolongan("Angular / V.a");
//            supervisor.setJabatan("Senior Angular");
            
//            Tim tim = new Tim();
//            tim.setIdTim("1");
//            tim.setNamaTim("Tim 1");
            
            AnggotaDanWajibPajakWrapper wp1 = new AnggotaDanWajibPajakWrapper();
            wp1.setIdWajibPajak("wp1");
            wp1.setNamaWajibPajak("KALEYOS");
            wp1.setJenisWp((short) 0);
            wp1.setIdTim("1");
            wp1.setNipPegawai("789456123");
            wp1.setNamaPegawai("Dadang Konelo");
            wp1.setPangkat("Komandam");
            wp1.setGolongan("Teratas");
            wp1.setJabatan("Bintang");
            
            AnggotaDanWajibPajakWrapper wp2 = new AnggotaDanWajibPajakWrapper();
            wp2.setIdWajibPajak("wp2");
            wp2.setNamaWajibPajak("BANGUS");
            wp2.setJenisWp((short) 0);
            wp2.setIdTim("1");
            wp2.setNipPegawai("987564213");
            wp2.setNamaPegawai("Bazid");
            wp2.setPangkat("Jendral");
            wp2.setGolongan("Tertengah");
            wp2.setJabatan("Kadet");
            
            List<AnggotaDanWajibPajakWrapper> wajibPajak = new ArrayList<AnggotaDanWajibPajakWrapper>();
            List<AnggotaDanWajibPajakWrapper> wajibPajak2 = new ArrayList<AnggotaDanWajibPajakWrapper>();
            wajibPajak.add(wp1);
            wajibPajak.add(wp2);
            wajibPajak2.add(wp2);
            
//            dummi.getTimWPWrappers().add(new TimWPWrapper(
//                    penanggungJawab,
//                    supervisor,
//                    tim,
//                    wajibPajak
//            ));
//            dummi.getTimWPWrappers().add(new TimWPWrapper(
//                    penanggungJawab,
//                    supervisor,
//                    tim,
//                    wajibPajak
//            ));

            dummi.getTimWPWrapperJaspers().add(new TimWPWrapperJasper(
                    "123456789",
                    "Bayu Arafli",
                    "Jendral",
                    "TI.VIa",
                    "Junior",
                    "245124",
                    "Bambang",
                    "Komandan",
                    "I.Va",
                    "Senior",
                    "Tim 1",
                    wajibPajak
            ));
            dummi.getTimWPWrapperJaspers().add(new TimWPWrapperJasper(
                    "123456789",
                    "Nata BAKA",
                    "Jendral",
                    "TI.VIa",
                    "Junior",
                    "245124",
                    "BAKA BAKA",
                    "Komandan",
                    "I.Va",
                    "Senior",
                    "Tim 2",
                    wajibPajak2
            ));
            
            
            
            
            //data dummi
            
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            String jasperPathFile = "file:///D://DaftarPetugasPemeriksa.jasper";
            String jrxmlPathFile = "D://DaftarPetugasPemeriksa.jrxml";
            
            JasperCompileManager.compileReportToFile(jrxmlPathFile);
                    
            JasperReport report = null;
            
            try {
                report = (JasperReport)JRLoader.loadObject(new URL(jasperPathFile));
            } catch (MalformedURLException ex) {
                System.out.println("MalformedURLException ex");
                Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_wrapper");
            
            JRBeanCollectionDataSource beanColDataSourceWp1 =
            new JRBeanCollectionDataSource(dummi.getTimWPWrapperJaspers().get(0).getWajibPajaks());
            dummi.getTimWPWrapperJaspers().get(0).setWajibPajakJasper(beanColDataSourceWp1);
            
            JRBeanCollectionDataSource beanColDataSourceWp2 =
            new JRBeanCollectionDataSource(dummi.getTimWPWrapperJaspers().get(1).getWajibPajaks());
            dummi.getTimWPWrapperJaspers().get(1).setWajibPajakJasper(beanColDataSourceWp2);
            
            JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dummi.getTimWPWrapperJaspers());
            
            Map parameter = new HashMap();
            /**
             * Passing ReportTitle and Author as parameters
             */
            DateFormat df_tanggal_pengesahan = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_dasar_tanggal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat df_biaya_tanggal_apbd = new SimpleDateFormat("dd MMMM yyyy");

            parameter.put("nomor_surat", dummi.getNomorSurat());
            parameter.put("tanggal_pengesahan", String.valueOf(df_tanggal_pengesahan.format(dummi.getTanggalPengesahan())));
            
            parameter.put("dasar_nomor", dummi.getDasarNomor());
            parameter.put("dasar_tanggal", String.valueOf(df_dasar_tanggal.format(dummi.getDasarTanggal())));
            parameter.put("dasar_tahun_anggaran", dummi.getDasarTahunAnggaran());
            parameter.put("nama_perintah", dummi.getNama());
            parameter.put("jabatan_perintah", dummi.getJabatan());
            parameter.put("masa_pajak_awal", String.valueOf(dummi.getMasaPajakAwalBulan())
                    + " " + String.valueOf(dummi.getMasaPajakAwalTahun()));
            parameter.put("masa_pajak_akhir", String.valueOf(dummi.getMasaPajakAkhirbulan())
                    + " " + String.valueOf(dummi.getMasaPajakAkhirTahun()));
            parameter.put("tahap_ke", String.valueOf(dummi.getTahapKe()));
            parameter.put("lama_pelaksanaan", String.valueOf(dummi.getLamaPelaksanaan()));
            parameter.put("biaya_tahun_apbd", String.valueOf(dummi.getBiayaTahunAPBD()));
            parameter.put("biaya_nomor_apbd", dummi.getBiayaNomorAPBD());
            parameter.put("biaya_tanggal_apbd", String.valueOf(df_biaya_tanggal_apbd.format(dummi.getBiayaTanggalAPBD())));
            parameter.put("ditetapkan_di", dummi.getDitetapkanDi());
            parameter.put("penandatangan", dummi.getPenandatangan());
            
//            parameter.put("nomor_surat", "jd");
//            parameter.put("tanggal_pengesahan", "ds");
//            
//            parameter.put("dasar_nomor", "ss");
//            parameter.put("dasar_tanggal", "ss");
//            parameter.put("dasar_tahun_anggaran", "ss");
//            parameter.put("nama_perintah", "ss");
//            parameter.put("jabatan_perintah", "ss");
//            parameter.put("masa_pajak_awal", "ss");
//            parameter.put("masa_pajak_akhir", "ss");
//            parameter.put("tahap_ke", "ss");
//            parameter.put("lama_pelaksanaan", "ss");
//            parameter.put("biaya_tahun_apbed", "ss");
//            parameter.put("biaya_nomor_apbed", "ss");
//            parameter.put("biaya_tanggal_apbed", "ss");
//            parameter.put("ditetapkan_di", "ss");
//            parameter.put("penandatangan", dummi.getPenandatangan());
            
            
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
    
}
