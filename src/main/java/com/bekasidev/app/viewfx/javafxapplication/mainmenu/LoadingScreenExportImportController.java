/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.mainmenu;

import com.bekasidev.app.view.util.SessionProvider;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class LoadingScreenExportImportController implements Initializable {

    @FXML private Text notifText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initData();
    }

    private void initData() {
        if (SessionProvider.getGlobalSessionsMap().get("notif_message_loading") != null) {
            
            String message
                    = (String) SessionProvider.getGlobalSessionsMap().get("notif_message_loading");
            
            notifText.setText(message);
        }
    }     
    
}
