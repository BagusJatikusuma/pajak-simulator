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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UIController implements Initializable {
    @FXML
    private Pane mainmenu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void closeApp(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
    
    public void openPersiapanPajakHotel(ActionEvent actionEvent) {
        Logger.getLogger(getClass().getName()).info("open persiapan pajak hotel");
        Logger.getLogger(getClass().getName()).info("parent "+mainmenu.getParent().getId());
        
        Pane rootpane = (Pane) mainmenu.getParent();
        //reset content
        rootpane.getChildren().remove(1);
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
}
