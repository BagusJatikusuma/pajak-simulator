/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.masterview.MasterWPPanel;
import com.bekasidev.app.view.masterview.MasterWpTableComponent;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.WPMasterTableWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
 * @author USER
 */
public class FormTambahWPUIController implements Initializable {
    private WajibPajakService wajibPajakService;

    @FXML private TextField npwpdField;
    @FXML private TextField namaWPField;
    @FXML private TextField alamatWPField;
    @FXML private TextField desaWPField;
    @FXML private TextField kecamatanWPField;
    @FXML private ChoiceBox jenisWPField;
    
    @FXML private Button cancelBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateJenisWP();
    }    
    
    public void cancelOperation() {
        System.out.println("cancel btn pressed");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void tambahWPOperation() {
        wajibPajakService = ServiceFactory.getWajibPajakService();
        
        if (!(npwpdField.getText().equals("")
                || namaWPField.getText().equals("")
                || desaWPField.getText().equals("")
                || kecamatanWPField.getText().equals("")
                || alamatWPField.getText().equals("")
                || jenisWPField.getValue().equals(""))) {
            
            WajibPajak wajibPajak = new WajibPajak();
            wajibPajak.setIdWajibPajak(npwpdField.getText());
            wajibPajak.setNamaWajibPajak(namaWPField.getText());
            wajibPajak.setJalan(alamatWPField.getText());
            wajibPajak.setDesa(desaWPField.getText());
            wajibPajak.setKecamatan(kecamatanWPField.getText());
            if (jenisWPField.getValue().equals("Restoran")) {
                wajibPajak.setJenisWp((short)0);
            }
            else if (jenisWPField.getValue().equals("Hotel")) {
                wajibPajak.setJenisWp((short)1);
            }
            else if(jenisWPField.getValue().equals("Parkir")) {
                wajibPajak.setJenisWp((short)2);
            }
            else if (jenisWPField.getValue().equals("Hiburan")) {
                wajibPajak.setJenisWp((short)3);
            }
            else if (jenisWPField.getValue().equals("Penerangan Jalan")) {
                wajibPajak.setJenisWp((short)4);
            }
            
            wajibPajakService.createDataWP(wajibPajak);
            System.out.println("wajib pajak berhasil dibuat");
            
            Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
            rootpane.getChildren().remove(1);
            
            Pane contentPane = null;
            try { 
                contentPane
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterWajibPajakUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().add(contentPane);
        
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
        else {
            //ada field yang kosong
        }
    }
    
    public void populateJenisWP() {
        jenisWPField.setItems(
                FXCollections.observableArrayList(
                        "Restoran", 
                        "Hotel",
                        "Parkir",
                        "Hiburan",
                        "Penerangan Jalan"));
    }
    
}
