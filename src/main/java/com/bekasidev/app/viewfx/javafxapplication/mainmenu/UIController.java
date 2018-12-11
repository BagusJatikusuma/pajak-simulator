/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.mainmenu;

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
import com.bekasidev.app.viewfx.javafxapplication.rootpane.RootPaneController;
import java.io.File;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.FileChooser;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            System.out.println("Ada kesalahan dalam memilih file");
            return;
        }
        
        
        
    }
    
    public void exportDatabase() {
        
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
    
}
