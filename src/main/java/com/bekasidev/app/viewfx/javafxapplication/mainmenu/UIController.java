/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.mainmenu;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.ExportImportService;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.bekasidev.app.viewfx.javafxapplication.JavaFXApplication;
import com.bekasidev.app.viewfx.javafxapplication.master.FormTambahWPUIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.rootpane.RootPaneController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UIController implements Initializable {
    @FXML
    private Pane mainmenu;
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem persiapanMenuItem;
    @FXML private MenuItem pelaksanaanMenuItem;
    @FXML private MenuItem pelaporanMenuItem;
    @FXML private MenuItem wajibPajakMenuItem;
    @FXML private MenuItem timPemeriksaMenuItem;
    @FXML private MenuItem pegawaiMenuItem;
    
    private ExportImportService exportImportService;
    private SuratPerintahService suratPerintahService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        exportImportService = ServiceFactory.getExportImportService();
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        
        closeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCodeCombination.CONTROL_DOWN));
        persiapanMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT1, KeyCodeCombination.CONTROL_DOWN));
        pelaksanaanMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT2, KeyCodeCombination.CONTROL_DOWN));
        pelaporanMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT3, KeyCodeCombination.CONTROL_DOWN));
        wajibPajakMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCodeCombination.CONTROL_DOWN));
        timPemeriksaMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCodeCombination.CONTROL_DOWN));
        pegawaiMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCodeCombination.CONTROL_DOWN));
    }    
    
    public void closeApp(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
    
    public void importDatabase() {
        System.out.println("Import database");
        Pane rootpane = (Pane) mainmenu.getParent();
        Stage primaryStage = (Stage) rootpane.getScene().getWindow();
        
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File sqlFile = fileChooser.showOpenDialog(primaryStage);
        
        if (sqlFile == null) {
            showErrorNotif("Ada kesalahan dalam memilih file");
            return;
        }
        
        Stage stage = initLoadingScreen("SEDANG MENGIMPORT DATA...");
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    exportImportService.importData(sqlFile);
                } catch (IOException ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.close();
                            showErrorNotif("Ada masalah ketika mengimport data");
                        }
                    });
                    Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                        showErrorNotif("Import data berhasil");
                    }
                });
            }
            
        };
        t.start();
        
    }
    
    public void exportDatabase() {
        System.out.println("export data");
        
        List<PersiapanWrapper> persiapanWrappers = new ArrayList<>();
        for (SuratPerintah sp : suratPerintahService.getAllSuratPerintah()) {
            PersiapanWrapper persiapanWrapper = ConverterHelper.convertSuratPerintahToPersiapanWrapper(sp);
            persiapanWrappers.add(persiapanWrapper);
        }
        SessionProvider
            .getGlobalSessionsMap()
            .put("daftar_persiapan_wrapper_popup", persiapanWrappers);
        
        try {
            Pane aboutPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PopupExportData.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Export data");
            stage.setMaximized(false);
            stage.setScene(new Scene(aboutPane));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Stage stage = initLoadingScreen("SEDANG MENGEXPORT DATA...");
//        
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    
//                    exportImportService.exportData();
//                } catch (IOException ex) {
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            stage.close();
//                            showErrorNotif("ada masalah saat mengexport data");
//                        }
//                    });
//                    
//                    Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        stage.close();
//                        showErrorNotif("export data berhasil");
//                    }
//                });
//            }
//            
//        };
//        t.start();
        
    }
    
    public void changePassword() {
        //tunggu service rony
        Pane aboutPane = null;
        try {
            aboutPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormChangePassword.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Ubah password");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(aboutPane));
        stage.showAndWait();
    }
    
    private void configureFileChooser(final FileChooser fileChooser) {      
            fileChooser.setTitle("Import database");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SQL FILE", "*.sql")
            );
    }
    
    public void openPersiapanPajakHotel(ActionEvent actionEvent) {
        Logger.getLogger(getClass().getName()).info("open persiapan pajak hotel");
        Logger.getLogger(getClass().getName()).info("parent "+mainmenu.getParent().getId());
        
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PajakHotelUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    public void openAbout(ActionEvent actionEvent) {
        try {
            Pane aboutPane = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/AboutUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About us");
            stage.setScene(new Scene(aboutPane));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openWajibPajak() {
        Logger.getLogger(getClass().getName()).info("open persiapan pajak hotel");
        Logger.getLogger(getClass().getName()).info("parent "+mainmenu.getParent().getId());
        
        Pane rootpane = (Pane) mainmenu.getParent();
        Logger.getLogger(getClass().getName()).info("in menus before "+rootpane.getChildren().get(1).getId()+" : "+rootpane.getChildren().get(0).getId());
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterWajibPajakUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rootpane.getChildren().add(contentPane);
        Logger.getLogger(getClass().getName()).info("in menus "+rootpane.getChildren().get(1).getId()+" : "+rootpane.getChildren().get(0).getId());
    }
    
    public void openPersiapanPajakRestoran() {
        Pane rootpane = (Pane) mainmenu.getParent();
        Logger.getLogger(getClass().getName()).info("in menus before restoran "+rootpane.getChildren().get(1).getId()+" : "+rootpane.getChildren().get(0).getId());
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/PajakRestoranUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    public void openMasterTim() {
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterTimUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    public void openMasterPegawai() {
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterPegawaiUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    public void openPersiapan() {
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try {
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PersiapanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    public void openPelaksanaan() {
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try {
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PelaksanaanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    public void openPelaporan() {
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
        Pane contentPane = null;
        try {
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/EvaluasiUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
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
