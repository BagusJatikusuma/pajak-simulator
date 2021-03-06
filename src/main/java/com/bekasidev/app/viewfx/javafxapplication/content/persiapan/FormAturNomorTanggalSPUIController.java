/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.BerkasPersiapanService;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.content.pelaporan.EvaluasiUIController;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenPinjamanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturNomorTanggalSPUIController implements Initializable {
    @FXML private DatePicker tanggalPengesahanField;
    @FXML private TextField nomorSuratField;
    @FXML private Button backToFormTimBtn;
    
    private ReportService reportService;
    private SuratPerintahService suratPerintahService;
    private BerkasPersiapanService berkasPersiapanService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        berkasPersiapanService = ServiceFactory.getBerkasPersiapanService();
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        
        System.out.println(rootpaneFormPersiapan.getMinHeight());
        System.out.println(rootpaneFormPersiapan.getPrefHeight());
        System.out.println(rootpaneFormPersiapan.getMaxHeight());
        
        Label headerLabel = (Label) SessionProvider.getGlobalSessionsMap()
                                            .get("header_form_persiapan");
        headerLabel.setText("FORM ATUR NOMOR & TANGGAL SURAT PERINTAH");
        headerLabel.setLayoutX(135);
        setDatePickerFormat();
        initData();
        suratPerintahService = ServiceFactory.getSuratPerintahService();
    }
    
    private void initData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        if (persiapanWrapper.getTanggalPengesahan() != null) {
            nomorSuratField.setText(persiapanWrapper.getNomorSurat());
            tanggalPengesahanField.setValue(persiapanWrapper
                                            .getTanggalPengesahan()
                                            .toInstant()
                                            .atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    public void backToFormTim() {
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturTimWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }
    
    public void saveTanggalNomorSP() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        if (tanggalPengesahanField.getValue() != null
                && !nomorSuratField.getText().equals("")) {
            persiapanWrapper.setTanggalPengesahan(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            persiapanWrapper.setNomorSurat(nomorSuratField.getText());
        }
        else {
            persiapanWrapper.setTanggalPengesahan(null);
            persiapanWrapper.setNomorSurat(null);
        }
        //simpan menggunakan suratPerintahService update
        boolean isUpdate = false;
        if (persiapanWrapper.getIdSP() != null) {
            isUpdate = true;
        }
        if (!isUpdate) {
            SuratPerintah suratPerintah
                    = ConverterHelper.convertPersiapanWrapperIntoSuratPerintah(persiapanWrapper);
            System.out.println("list tim "+suratPerintah.getListTim().size());
            for (TimSP timSP : suratPerintah.getListTim()) {
                System.out.println("anggota "+timSP.getNamaTim()+" : "+timSP.getListAnggota().size());
            }
            persiapanWrapper.setIdSP(suratPerintahService.createSuratPerintah(suratPerintah).getIdSP());
            System.out.println("create success");
        }
        else {
            SuratPerintah sp = ConverterHelper.convertPersiapanWrapperIntoSuratPerintah(persiapanWrapper);
            suratPerintahService.updateSuratPerintah(sp);
            suratPerintahService.updateTim(sp.getListTim());
            System.out.println("update success "+sp.getNomorUrut()+" - "+sp.getTempat()+" : "+sp.getIdSP());
        }
        
        Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
        rootpane.getChildren().remove(1);

        Pane contentPane = null;
        try {
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PersiapanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
        
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Berhasil disimpan");
        Pane popup = null;
        try {
            popup = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/PopupPaneUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(popup));
        stage.show();
        
//        Stage stage = (Stage) cancelBtn.getScene().getWindow();
//        stage.close();
    }
    
    public void printSuratPerintah() {
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
//        for (TimWPWrapper obj : persiapanWrapper.getTimWPWrappers()) {
//            System.out.println("Tim : "+obj.getTim().getNamaTim());
//            System.out.println("Penanggung jawab : "+obj.getPenanggungJawab().getNamaPegawai());
//            System.out.println("Supervisor : "+obj.getSupervisor().getNamaPegawai());
//            for (WajibPajak wp : obj.getWajibPajaks()) {
//                System.out.println("WP "+wp.getNamaWajibPajak());
//            }
//        }
        
//        reportService.createSuratPerintah();
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoadingTest.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(contentPane));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratPerintahBaru();
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
//        reportService.createDaftarPetugasPemeriksa();
    }
    
    
    
    public void aturSuratPemberitahuan() {
        //====================================================================================//
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        if (persiapanWrapper.getIdSP()==null) {
            SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Surat Perintah belum disimpan");
            
            Pane popup = null;
            try {
                popup = FXMLLoader
                        .load(getClass().getClassLoader().getResource("fxml/PopupPaneUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(popup));

            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            return;
        }
        
        if (tanggalPengesahanField.getValue() != null
                && !nomorSuratField.getText().equals("")) {
            persiapanWrapper.setTanggalPengesahan(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            persiapanWrapper.setNomorSurat(nomorSuratField.getText());
        }
        
        List<TimWPWrapper> timWPWrappers 
                = persiapanWrapper.getTimWPWrappers();
        //reset array before
        Map<String, NomorBerkasContainer> nomMap = new HashMap<>();
        for (NomorTanggalWajibPajakWrapper obj : persiapanWrapper.getNomorTanggalWPList()) {
            System.out.println("nomor pin "+obj.getNomorPeminjamanDokumen());
            nomMap.put(obj.getWajibPajak().getNpwpd(), 
                    new NomorBerkasContainer(
                            obj.getNomorPemberitahuanPemeriksaan(), 
                            obj.getTanggalPemberitahuanPemeriksaan(),
                            obj.getNomorPeminjamanDokumen(),
                            obj.getTanggalPeminjamanDokumen()));
        }
        persiapanWrapper.getNomorTanggalWPList().clear();
        for (TimWPWrapper obj : timWPWrappers) {
            for (WajibPajak objWP : obj.getWajibPajaks()) {
                NomorBerkasContainer nm = nomMap.get(objWP.getNpwpd());
                persiapanWrapper.getNomorTanggalWPList().add(new NomorTanggalWajibPajakWrapper(
                        objWP,
                        (nm!=null)?nm.getNomorPemberitahuanPemeriksaan():null,
                        (nm!=null)?nm.getTanggalPemberitahuanPemeriksaan():null,
                        (nm!=null)?nm.getNomorPeminjamanDokumen():null,
                        (nm!=null)?nm.getTanggalPeminjamanDokumen():null
                ));
                
            }
        }
        //synchronisasi dokumen pinjaman jika ada perubahan pada beberapa attr persiapan wrapper
        syncDokumenPinjamanList(persiapanWrapper);
        //====================================================================================//
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturNomorTanggalSuratPemberitahuanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }
    
    private void setDatePickerFormat() {
        tanggalPengesahanField.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter
                    =DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.forLanguageTag("id-ID"));

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
       
    }
    
    private class NomorBerkasContainer {
        private String nomorPemberitahuanPemeriksaan;
        private Date tanggalPemberitahuanPemeriksaan;
        private String nomorPeminjamanDokumen;
        private Date tanggalPeminjamanDokumen;

        public NomorBerkasContainer() {
        }

        public NomorBerkasContainer(String nomorPemberitahuanPemeriksaan, Date tanggalPemberitahuanPemeriksaan, String nomorPeminjamanDokumen, Date tanggalPeminjamanDokumen) {
            this.nomorPemberitahuanPemeriksaan = nomorPemberitahuanPemeriksaan;
            this.tanggalPemberitahuanPemeriksaan = tanggalPemberitahuanPemeriksaan;
            this.nomorPeminjamanDokumen = nomorPeminjamanDokumen;
            this.tanggalPeminjamanDokumen = tanggalPeminjamanDokumen;
        }

        

        public String getNomorPemberitahuanPemeriksaan() {
            return nomorPemberitahuanPemeriksaan;
        }

        public void setNomorPemberitahuanPemeriksaan(String nomorPemberitahuanPemeriksaan) {
            this.nomorPemberitahuanPemeriksaan = nomorPemberitahuanPemeriksaan;
        }

        public Date getTanggalPemberitahuanPemeriksaan() {
            return tanggalPemberitahuanPemeriksaan;
        }

        public void setTanggalPemberitahuanPemeriksaan(Date tanggalPemberitahuanPemeriksaan) {
            this.tanggalPemberitahuanPemeriksaan = tanggalPemberitahuanPemeriksaan;
        }

        public String getNomorPeminjamanDokumen() {
            return nomorPeminjamanDokumen;
        }

        public void setNomorPeminjamanDokumen(String nomorPeminjamanDokumen) {
            this.nomorPeminjamanDokumen = nomorPeminjamanDokumen;
        }

        public Date getTanggalPeminjamanDokumen() {
            return tanggalPeminjamanDokumen;
        }

        public void setTanggalPeminjamanDokumen(Date tanggalPeminjamanDokumen) {
            this.tanggalPeminjamanDokumen = tanggalPeminjamanDokumen;
        }
        
        
    }
    
    private void syncDokumenPinjamanList(PersiapanWrapper persiapanWrapper) {
        String masaPajakAwal 
                    = ConverterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAwalBulan())
                        + " "
                        +persiapanWrapper.getMasaPajakAwalTahun();
        String masaPajakAkhir
                    = ConverterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAkhirbulan())
                        + " "
                        +persiapanWrapper.getMasaPajakAkhirTahun();
        for (DokumenPinjamanWajibPajakWrapper wrapper 
                : persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()) {
            wrapper.getWajibPajak().getListPinjaman().clear();
//            wrapper.getListPinjaman().clear();
            berkasPersiapanService
                    .getDokumenPinjaman(wrapper.getWajibPajak(), masaPajakAwal, masaPajakAkhir);
//            wrapper.getListPinjaman().addAll(wrapper.getWajibPajak().getListPinjaman());
        }
    }
    
}
