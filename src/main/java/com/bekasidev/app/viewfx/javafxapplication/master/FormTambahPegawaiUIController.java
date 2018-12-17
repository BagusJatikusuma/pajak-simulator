/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.MasterPegawaiTableWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormTambahPegawaiUIController implements Initializable {
    private PegawaiService service;
    
    @FXML private TextField nipField;
    @FXML private TextField namaField;
    @FXML private TextField pangkatField;
    @FXML private TextField golonganField;
    @FXML private TextField jabatanField;
    
    @FXML private Label formLabel;
    
    @FXML private Button cancelBtn;
    @FXML private Button tambahBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateData();
    }
    
    public void populateData() {
        Pegawai pegawai
                = (Pegawai) SessionProvider.getGlobalSessionsMap().get("update_pegawai_selected");
        if (pegawai != null) {
            formLabel.setText("FORM UPDATE PEGAWAI");
            
            nipField.setText(pegawai.getNipPegawai());
            namaField.setText(pegawai.getNamaPegawai());
            pangkatField.setText(pegawai.getPangkat());
            golonganField.setText(pegawai.getGolongan());
            jabatanField.setText(pegawai.getJabatanDinas());
            
            tambahBtn.setText("Update");
        }
    }

    public void tambahOperation() {
        service = ServiceFactory.getPegawaiService();
        Pegawai pegawaiUpdate = (Pegawai) SessionProvider.getGlobalSessionsMap().get("update_pegawai_selected");
        if (!(nipField.getText().equals("")
                || namaField.getText().equals("")
                || pangkatField.getText().equals("")
                || golonganField.getText().equals("")
                || jabatanField.getText().equals(""))) {
            Pegawai pegawai 
                    = new Pegawai(
                            (pegawaiUpdate == null)?"":pegawaiUpdate.getIdTim(), 
                            nipField.getText(), 
                            namaField.getText(), 
                            golonganField.getText(),
                            pangkatField.getText(),
                            (pegawaiUpdate == null)?"":pegawaiUpdate.getJabatanTim(),
                            jabatanField.getText());
            if (pegawaiUpdate == null) {
                service.createPegawai(pegawai);
            }else{
                service.updatePegawai(pegawai, pegawaiUpdate.getNipPegawai());
            }
            Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
            rootpane.getChildren().remove(1);
            
            Pane contentPane = null;
            try { 
                contentPane
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterPegawaiUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().add(contentPane);
        
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }
    
    public void cancelOperation() {
        System.out.println("cancel btn pressed");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
}
