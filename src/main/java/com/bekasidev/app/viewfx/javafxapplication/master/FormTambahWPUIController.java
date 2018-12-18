/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FormTambahWPUIController implements Initializable {
    private WajibPajakService wajibPajakService;

    @FXML private TextField npwpdField;
    @FXML private TextField namaWPField;
    @FXML private TextField alamatWPField;
    @FXML private TextField desaWPField;
    @FXML private TextField kecamatanWPField;
    @FXML private ChoiceBox jenisWPField;
    
    @FXML private Button cancelBtn;
    @FXML private Button tambahBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateData();
        populateJenisWP();
        WajibPajak wajibPajak
                = (WajibPajak) SessionProvider.getGlobalSessionsMap().get("update_wp_selected");
        if (wajibPajak!=null)
            tambahBtn.setText("Update");
    }

    public void populateData() {
        WajibPajak wajibPajak
                = (WajibPajak) SessionProvider.getGlobalSessionsMap().get("update_wp_selected");
        if (wajibPajak!=null) {
            npwpdField.setText(wajibPajak.getNpwpd());
            namaWPField.setText(wajibPajak.getNamaWajibPajak());
            alamatWPField.setText(wajibPajak.getJalan());
            desaWPField.setText(wajibPajak.getDesa());
            kecamatanWPField.setText(wajibPajak.getKecamatan());
        }
        
    }
    
    public void cancelOperation() {
        SessionProvider.getGlobalSessionsMap().put("update_wp_selected", null);
        System.out.println("cancel btn pressed");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void tambahWPOperation() {
        wajibPajakService = ServiceFactory.getWajibPajakService();
        WajibPajak wajibPajakUpdate
                = (WajibPajak) SessionProvider.getGlobalSessionsMap().get("update_wp_selected");
        
        if (!(npwpdField.getText().equals("")
                || namaWPField.getText().equals("")
                || alamatWPField.getText().equals("")
                || jenisWPField.getValue().equals(""))) {
            
            WajibPajak wajibPajak = new WajibPajak();
            wajibPajak.setNpwpd(npwpdField.getText());
            wajibPajak.setNamaWajibPajak(namaWPField.getText());
            wajibPajak.setJalan(alamatWPField.getText());
            wajibPajak.setDesa(desaWPField.getText());
            wajibPajak.setKecamatan(kecamatanWPField.getText());
            if (jenisWPField.getValue().equals("Restoran")) {
                wajibPajak.setJenisWp((short)0);
            }
            else if (jenisWPField.getValue().equals("Hotel")) {
                wajibPajak.setJenisWp((short)1);
            }
            else if(jenisWPField.getValue().equals("Parkir")) {
                wajibPajak.setJenisWp((short)2);
            }
            else if (jenisWPField.getValue().equals("Hiburan")) {
                wajibPajak.setJenisWp((short)3);
            }
            else if (jenisWPField.getValue().equals("Penerangan Jalan")) {
                wajibPajak.setJenisWp((short)4);
            }
            
            if (wajibPajakUpdate == null) {
                wajibPajakService.createDataWP(wajibPajak);
                System.out.println("wajib pajak berhasil dibuat");
            } else {
                wajibPajakService.updateWajibPajak(wajibPajak);
                System.out.println("wajib pajak berhasil diupdate");
            }
            
            System.out.println("proses wp finished 1");
            Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
            rootpane.getChildren().remove(1);
            
            Pane contentPane = null;
            try { 
                contentPane
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterWajibPajakUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().add(contentPane);
        
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            System.out.println("proses wp finished");
            SessionProvider.getGlobalSessionsMap().put("update_wp_selected", null);
        }
        else {
            //ada field yang kosong
            showEmptyFieldErrorNotif();
        }
    }
    
    public void populateJenisWP() {
        jenisWPField.setItems(
                FXCollections.observableArrayList(
                        "Restoran", 
                        "Hotel",
                        "Parkir",
                        "Hiburan",
                        "Penerangan Jalan"));
        WajibPajak wajibPajakUpdate
                = (WajibPajak) SessionProvider.getGlobalSessionsMap().get("update_wp_selected");
        if (wajibPajakUpdate!=null) {
            switch (wajibPajakUpdate.getJenisWp()) {
                case 0 :
                    jenisWPField.getSelectionModel().select(0);
                    break;
                case 1 :
                    jenisWPField.getSelectionModel().select(1);
                    break;
                case 2 :
                    jenisWPField.getSelectionModel().select(2);
                    break;
                case 3 :
                    jenisWPField.getSelectionModel().select(3);
                    break;
                default: break;
            }
        }
    }
    
    private void showEmptyFieldErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Ada kolom yang kosong");
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
    
    
    
}
