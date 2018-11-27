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
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        suratPerintahService = ServiceFactory.getSuratPerintahService();
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
        SuratPerintah suratPerintah
                = ConverterHelper.convertPersiapanWrapperIntoSuratPerintah(persiapanWrapper);
        System.out.println("list tim "+suratPerintah.getListTim().size());
        for (TimSP timSP : suratPerintah.getListTim()) {
            System.out.println("anggota "+timSP.getNamaTim()+" : "+timSP.getListAnggota().size());
        }
        suratPerintahService.createSuratPerintah(suratPerintah);
        System.out.println("create success");
        
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
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
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
        for (TimWPWrapper obj : timWPWrappers) {
            for (WajibPajak objWP : obj.getWajibPajaks()) {
                persiapanWrapper.getNomorTanggalWPList().add(new NomorTanggalWajibPajakWrapper(
                        objWP,
                        null,
                        null,
                        null,
                        null
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
    
    
    
}
