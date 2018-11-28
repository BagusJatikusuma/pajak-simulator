/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenPinjamanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormInputDokumenWPUIController implements Initializable {
    @FXML private TextField namaDokumenField;
    @FXML private TextField keteranganField;
    @FXML private Button cancelBtn;

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
//        PersiapanWrapper persiapanWrapper
//                = (PersiapanWrapper) SessionProvider
//                .getGlobalSessionsMap()
//                .get("persiapan_wrapper");
        List<DokumenPinjaman> dokumenTempList
                = (List<DokumenPinjaman>) SessionProvider
                    .getGlobalSessionsMap()
                    .get("dokumen_temp_list");
//        String selectedWP 
//                = (String) SessionProvider
//                    .getGlobalSessionsMap()
//                    .get("selected_dokumen_wp");
        dokumenTempList.add(new DokumenPinjaman(
                                namaDokumenField.getText(), 
                                keteranganField.getText()));
//        for (DokumenPinjamanWajibPajakWrapper obj
//                :dokumenTempList) {
//            if (obj.getWajibPajak().getNpwpd()
//                    .equals(selectedWP)) {
//                obj.getWajibPajak()
//                        .getListPinjaman()
//                        .add(new DokumenPinjaman(
//                                namaDokumenField.getText(), 
//                                keteranganField.getText()));
//                break;
//            }
//            
//        }
        //=================================================================//
        Stage stageAturDokumenWP 
                = (Stage) SessionProvider
                    .getGlobalSessionsMap()
                    .get("stage_atur_dokumen_wp");
        Pane formAturDokumenWP = null;
        try {
            formAturDokumenWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormAturDokumenWP.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stageAturDokumenWP.setScene(new Scene(formAturDokumenWP));
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
}
