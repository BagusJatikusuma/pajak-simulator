/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaporan;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormPrintRootController implements Initializable {
    @FXML private Pane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComponentCollectorProvider
                .addFxComponent("root_pane_form_print_laporan", rootPane);
        Pane formPrintLaporan = null;
        try {
            formPrintLaporan = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormPrintLaporan.fxml"));
            rootPane.getChildren().add(formPrintLaporan);
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
