/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormDaftarRekapitulasiPerbandinganPendapatanDetailController implements Initializable {

    @FXML private Button cancelBtn;
    @FXML private TextField omzetHasilField;
    @FXML private TextField omzetDiLaporkanField;
    
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
    
    public void finishOperation() {
        Rekapitulasi rek
                = (Rekapitulasi) SessionProvider
                    .getGlobalSessionsMap()
                    .get("selected_bulan_rekapitulasi");
        if (!(omzetDiLaporkanField.getText().equals("") 
                ||omzetHasilField.getText().equals(""))) {
            System.out.println("setter now");
            rek.setOmzetHasilPeriksa(Double.valueOf(omzetHasilField.getText()));
            rek.setOmzetLaporan(Double.valueOf(omzetDiLaporkanField.getText()));
        }
        
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormDaftarRekapitulasiPerbandinganPendapatan.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
}
