/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FormInputSuratPemberitahuanNomorTanggalController implements Initializable {
    @FXML private Button cancelBtn;
    @FXML private TextField nomorSuratField;
    @FXML private DatePicker tanggalPengesahanField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void tambahOperation() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        String npwpd 
                = (String) SessionProvider.getGlobalSessionsMap()
                                    .get("selected_wp_nomor_tanggal_input");
        for (NomorTanggalWajibPajakWrapper obj
                :persiapanWrapper.getNomorTanggalWPList()) {
            if (obj.getWajibPajak().getNpwpd()
                    .equals(npwpd)) {
                obj.setNomorPemberitahuanPemeriksaan(nomorSuratField.getText());
                obj.setTanggalPemberitahuanPemeriksaan(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            
        }
        
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
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        
    }
    
}
