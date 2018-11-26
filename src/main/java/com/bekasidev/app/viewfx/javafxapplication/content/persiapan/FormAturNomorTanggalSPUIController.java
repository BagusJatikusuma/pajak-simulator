/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturNomorTanggalSPUIController implements Initializable {
    @FXML private DatePicker tanggalPengesahanField;
    @FXML private TextField nomorSuratField;
    
    private ReportService reportService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cancelOperation() {
        
    }
    
    public void saveTanggalNomorSP() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        System.out.println("date "+tanggalPengesahanField.getValue());
        persiapanWrapper.setTanggalPengesahan(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        persiapanWrapper.setNomorSurat(nomorSuratField.getText());
        
        //simpan menggunakan suratPerintahService update
        
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
