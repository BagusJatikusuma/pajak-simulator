/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.login;

import com.bekasidev.app.service.ServiceFactory;
import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    
    boolean statusLogin;
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
            noticeField.setAlignment(Pos.CENTER);
        } else if(usernameField.getText().contains(" ")) {
            noticeField.setVisible(true);
            noticeField.setText("Username tidak menggunakan spasi");
            noticeField.setAlignment(Pos.CENTER);
        } else if(usernameField.getText().length() < 18){
            noticeField.setVisible(true);
            noticeField.setText("Username tidak boleh kurang dari 18");
            noticeField.setAlignment(Pos.CENTER);
        } else {
            //service
            statusLogin = ServiceFactory.getUserLoginService().login(usernameField.getText(), passwordField.getText());
            if(statusLogin == true){
                System.out.println("Berhasil masuk");
                showMainApp();
            } else {
                noticeField.setVisible(true);
                noticeField.setText("Username dan Password yang Anda masukkan salah!");
                noticeField.setAlignment(Pos.CENTER);
            }
        }
    }
    
    private void showMainApp() {
        closeLoginFrame();
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        Stage primaryStage = new Stage();
        Image img = new Image(getClass().getResourceAsStream("/images/logo_kab_bekasi.png"));
        primaryStage.getIcons().add(img);
        
        primaryStage.setTitle("Aplikasi Pemeriksaan Pajak");
        primaryStage.setMaximized(true);
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/RootPane.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FormLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
        
    }
    
    private void closeLoginFrame() {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
    }
}
