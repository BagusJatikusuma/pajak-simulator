/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormPelaksanaanContentUIController implements Initializable {
    @FXML private ChoiceBox suratPerintahField;
    @FXML private ChoiceBox timField;
    @FXML private ChoiceBox wajibPajakField;
    
    private Map<String, List<WajibPajak>> timWPMap = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateChoiceBox();
    }    

    private void populateChoiceBox() {
        List<PersiapanWrapper> persiapanWrappers
                = (List<PersiapanWrapper>) SessionProvider
                        .getGlobalSessionsMap()
                        .get("daftar_persiapan_wrapper");
        
        ObservableList<PersiapanWrapper> ovSuratPerintah 
                = FXCollections.observableArrayList();
        ObservableList<Tim> ovTim 
                = FXCollections.observableArrayList();
        ObservableList<WajibPajak> ovWP 
                = FXCollections.observableArrayList();
        for (PersiapanWrapper pw : persiapanWrappers) {
            ovSuratPerintah.add(pw);
        }
        suratPerintahField.setItems(ovSuratPerintah);
        suratPerintahField
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue ov, Object t, Object t1) {
                        PersiapanWrapper obj = (PersiapanWrapper) t1;
                        System.out.println(obj.toString());
                        
                        ovTim.clear();
                        timWPMap.clear();
                        
                        if (t1 == null) {
                            timField.getItems().clear();
                            timField.setDisable(true);
                        } else {
                            // sample code, adapt as needed:
                            for (TimWPWrapper timWP
                                    :obj.getTimWPWrappers()) {
                                ovTim.add(timWP.getTim());
                                timWPMap.put(timWP.getTim().getIdTim(), timWP.getWajibPajaks());
                            }
                            timField.getItems().setAll(ovTim);
                            timField.setDisable(false);
                        }
                        
                    }
                });
        
        timField
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue ov, Object t, Object t1) {
                        Tim tim = (Tim) t1;
                        List<WajibPajak> wajibPajaks = timWPMap.get(tim.getIdTim());
                        
                        System.out.println("tim choosed "+tim.getNamaTim());
                        
                        if (t1 == null) {
                            wajibPajakField.getItems().clear();
                            wajibPajakField.setDisable(true);
                        } else {
                            // sample code, adapt as needed:
                            for (WajibPajak wp
                                    :wajibPajaks) {
                                ovWP.add(wp);
                            }
                            wajibPajakField.getItems().setAll(ovWP);
                            wajibPajakField.setDisable(false);
                        }
                    }
                });
        
        
    }
    
    public void openFormDaftarRekapitulasi() {
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
    }
    
}
