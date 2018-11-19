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
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormTambahAnggotaTimUIController implements Initializable {
    @FXML private ChoiceBox calonAnggotaField;
    private PegawaiService service;
    private ObservableList<Pegawai> pegawaiChoiceBox;
    
    @FXML private Button cancelBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateDataCalonAnggota();
    }

    public void tambahOperation() {
        if (!calonAnggotaField.getValue().equals("")) {
            Pegawai obj = (Pegawai) calonAnggotaField.getSelectionModel().getSelectedItem();
            String idTim = (String) SessionProvider.getSessionAturAnggotaTimUIMap()
                    .get("selected_tim");
            service.setPegawaiTim(obj.getNipPegawai(), idTim);
            
            Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
            rootpane.getChildren().remove(1);
            
            Pane contentPane = null;
            try { 
                contentPane
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterPegawaiTimUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().add(contentPane);
        
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            
        } else {
            
        }
        
    }
    
    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    private void populateDataCalonAnggota() {
        service = ServiceFactory.getPegawaiService();
        List<Pegawai> calonAnggotaList
                = service.getAllPegawai();
        pegawaiChoiceBox = new ObservableArrayList<>();
        for (Pegawai obj : calonAnggotaList) {
            pegawaiChoiceBox.add(obj);
        }
        calonAnggotaField
                .setItems(pegawaiChoiceBox);
        
    }
    
}
