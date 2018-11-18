/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.service.reportservice.reportserviceimpl;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
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
            
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
            persiapanPajakPOJO.getTimPemeriksa().getAnggotaTims().add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));

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
            
//            DataBeanList DataBeanList = new DataBeanList();
//            ArrayList<Pegawai> dataList = new ArrayList<>();
//            dataList.add(new Pegawai("", "", "", "", ""));
//            
//            dataList.add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
//            dataList.add(new Pegawai("02", "141511057", "ABDUR", "IV.a", "ADMIN"));
//            dataList.add(new Pegawai("03", "141511056", "RISA", "IV.a", "TESTER"));
//            System.out.println("aaaaaaa " + dataList.size());
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
            
//            DataBeanList DataBeanList = new DataBeanList();
//            ArrayList<Pegawai> dataList = new ArrayList<>();
//            dataList.add(new Pegawai("", "", "", "", ""));
//            
//            dataList.add(new Pegawai("01", "141511058", "RONY NATA", "IV.a", "PROGRAMMER"));
//            dataList.add(new Pegawai("02", "141511057", "ABDUR", "IV.a", "ADMIN"));
//            dataList.add(new Pegawai("03", "141511056", "RISA", "IV.a", "TESTER"));
//            System.out.println("aaaaaaa " + dataList.size());
            PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_hotel");

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
    
}
