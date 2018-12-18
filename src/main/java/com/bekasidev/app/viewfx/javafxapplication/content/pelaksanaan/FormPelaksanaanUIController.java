/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
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
 * @author Bayu Arafli
 */
public class FormPelaksanaanUIController implements Initializable {
    @FXML private Pane formDokumenPelaksanaanPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComponentCollectorProvider
                .addFxComponent("root_form_pelaksanaan_ui", formDokumenPelaksanaanPane);
//        formDokumenPelaksanaanPane.getChildren().remove(0);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanSPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        formDokumenPelaksanaanPane.getChildren().add(contentPane);
    }    
    
}
