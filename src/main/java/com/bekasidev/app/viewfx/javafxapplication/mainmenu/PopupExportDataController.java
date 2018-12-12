/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.mainmenu;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.ExportImportService;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class PopupExportDataController implements Initializable {
    @FXML private ChoiceBox suratPerintahField;
    @FXML private ChoiceBox timField;
    @FXML private Button exportDataBtn;
    
    private SuratPerintahService suratPerintahService;
    private ExportImportService exportImportService;
    
    ObservableList<PersiapanWrapper> ovSuratPerintah;
    ObservableList<Tim> ovTim;
    ObservableList<WajibPajak> ovWP;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        exportImportService = ServiceFactory.getExportImportService();
        populateChoiceBox();
    }

    private void populateChoiceBox() {
        List<PersiapanWrapper> persiapanWrappers
                = (List<PersiapanWrapper>) SessionProvider
                        .getGlobalSessionsMap()
                        .get("daftar_persiapan_wrapper_popup");
        ovSuratPerintah 
                = FXCollections.observableArrayList();
        ovTim 
                = FXCollections.observableArrayList();
        ovWP 
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
                        
                        if (t1 == null) {
                            timField.getItems().clear();
                            timField.setDisable(true);
                        } else {
                            // sample code, adapt as needed:
                            for (TimWPWrapper timWP
                                    :obj.getTimWPWrappers()) {
                                ovTim.add(timWP.getTim());
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
                    }
                });
        
        
    }
    
    public void exportData() {
        PersiapanWrapper persiapanWrapper
                =(PersiapanWrapper) suratPerintahField
                    .getSelectionModel()
                    .getSelectedItem();
        Tim timSelected
                = (Tim) timField
                    .getSelectionModel()
                    .getSelectedItem();
        SuratPerintah sp = ConverterHelper.convertPersiapanWrapperIntoSuratPerintah(persiapanWrapper);
        TimSP timSP = suratPerintahService.getTimSP(sp.getIdSP(), timSelected.getIdTim());
        
        Stage stage = initLoadingScreen("SEDANG MENGEXPORT DATA...");
        
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    exportImportService.exportData();
                } catch (IOException ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.close();
                            showErrorNotif("ada masalah saat mengexport data");
                        }
                    });
                    
                    Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                        showErrorNotif("export data berhasil");
                        Stage thisStage = (Stage) exportDataBtn.getScene().getWindow();
                        thisStage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    
    private void showErrorNotif(String errMessage) {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", errMessage);
        Pane popup = null;
        try {
            popup = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/PopupPaneUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(popup));
        stage.show();
    }
    
    private Stage initLoadingScreen(String message) {
        if (message != null) {
            SessionProvider.getGlobalSessionsMap().put("notif_message_loading", message);
        }
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoadingScreenExportImport.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(contentPane));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
        return stage;
    }
    
}
