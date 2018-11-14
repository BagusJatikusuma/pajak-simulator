/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.rootpane;

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
 * @author USER
 */
public class RootPaneController implements Initializable {
    @FXML
    private Pane rootpane;
    private Pane mainMenuPane;
    private Pane contentPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mainMenuPane 
                    = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/UI.fxml"));
            contentPane 
                    = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/PajakRestoranUI.fxml"));
            
            rootpane.getChildren().add(mainMenuPane);
            rootpane.getChildren().add(contentPane);
            
        } catch (IOException ex) {
            System.out.println("resource not found");
            Logger.getLogger(RootPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
