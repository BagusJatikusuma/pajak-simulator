/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormTambahTimUIController implements Initializable {
    private PegawaiService service;
    @FXML private TextField timIdField;
    @FXML private TextField namaTimField;
    
    @FXML private Button cancelBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cancelOperation() {
        System.out.println("cancel btn pressed");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }   
    
    public void tambahOperation() {
        service = ServiceFactory.getPegawaiService();
        if (!(timIdField.getText().equals("")
                || namaTimField.getText().equals("")
                )) {
            Tim tim = new Tim(timIdField.getText(), namaTimField.getText());
            service.createTim(tim);
            
            Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
            rootpane.getChildren().remove(1);
            
            Pane contentPane = null;
            try { 
                contentPane
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterTimUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().add(contentPane);
        
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
        
    }
    
}
