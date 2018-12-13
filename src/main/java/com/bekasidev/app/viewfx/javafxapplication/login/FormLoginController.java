/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.login;

import javafx.scene.image.Image;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormLoginController implements Initializable {
    
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label noticeField;
    
    @FXML private Button loginBtn;
    @FXML private ImageView logoImage;
    
    boolean statusLogin = true;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("/images/logo_kab_bekasi.png"));
        logoImage.setImage(image);
        noticeField.setVisible(false);
    }    
    
    public void login(){       
        System.out.println("login button");
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        
        if(usernameField.getText().equals("") || passwordField.getText().equals("")){
            noticeField.setVisible(true);
            noticeField.setText("Form belum terisi");
        }else {
            //service
            if(statusLogin == true){
                System.out.println("Berhasil masuk");
                statusLogin = false;
            } else {
                noticeField.setVisible(true);
                noticeField.setText("Username dan Password yang Anda masukkan salah!");
                statusLogin = true;
            }
        }
    }
}
