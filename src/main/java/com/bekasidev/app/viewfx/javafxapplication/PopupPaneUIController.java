/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication;

import com.bekasidev.app.view.util.SessionProvider;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class PopupPaneUIController implements Initializable {

    @FXML private Label notifLabel;
    @FXML private Button okBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initData();
    }

    private void initData() {
        if (SessionProvider.getGlobalSessionsMap().get("notif_message_popup") != null) {
            
            String message
                    = (String) SessionProvider.getGlobalSessionsMap().get("notif_message_popup");
            
            notifLabel.setLayoutX(notifLabel.getLayoutX() - ((message.length()/2)*5) - 8);
            
            notifLabel.setText(message);
        }
    }
    
    public void close() {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
    
}
