/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturNomorTanggalSPUIController implements Initializable {
    @FXML private DatePicker tanggalPengesahanField;
    @FXML private TextField nomorSuratField;
    @FXML private Button cancelBtn;
    
    private ReportService reportService;
    private SuratPerintahService suratPerintahService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public void cancelOperation() {
        
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
            suratPerintahService.createSuratPerintah(suratPerintah);
            System.out.println("create success");
        }
        else {
            System.out.println(
                "update nomor surat sp "+
                persiapanWrapper.getIdSP()+
                " is "+
                nomorSuratField.getText());
            suratPerintahService
                    .setNomorUrut(
                            persiapanWrapper.getIdSP(), 
                            nomorSuratField.getText(),
                            String.valueOf(persiapanWrapper.getTanggalPengesahan().getTime()));
            System.out.println("update success");
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
        
        reportService.createSuratPerintah();
        reportService.createDaftarPetugasPemeriksa();
    }
    
    public void aturSuratPemberitahuan() {
        //====================================================================================//
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
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
    
}
