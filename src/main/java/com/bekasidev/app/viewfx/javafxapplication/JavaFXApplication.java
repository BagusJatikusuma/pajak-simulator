/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class JavaFXApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Aplikasi Perpajakan");
            primaryStage.setMaximized(true);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/RootPane.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("javafxresources/rootpane.css").toString());
            primaryStage.setScene(scene);
//            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
    
}
