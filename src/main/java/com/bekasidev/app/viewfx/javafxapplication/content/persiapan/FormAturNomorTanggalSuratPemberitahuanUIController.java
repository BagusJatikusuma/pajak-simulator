/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPemberitahuanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturNomorTanggalSuratPemberitahuanUIController implements Initializable {
    @FXML private TableView AturNomorTanggalSuratPemberitahuanTable;
    private TableColumn idWP;
    private TableColumn namaWP;
    private TableColumn nomorSurat;
    private TableColumn tanggalSurat;
    private TableColumn action;
    
    private ObservableList<PersiapanNomorTanggalSuratPemberitahuanTableWrapper> dataCollection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        AturNomorTanggalSuratPemberitahuanTable.setItems(dataCollection);
    }    
    
    public void simpanNomorTanggalSuratPemberitahuan() {
        
    }
    
    public void cancelOperation() {
        
    }
    
    public void printSuratPemberitahuan() {
        
    }
    
    public void aturSuratPeminjaman() {
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturNomorTanggalSuratPeminjamanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }

    private void addFromFXML() {
        idWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "Id WP");
        namaWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "Nama WP");
        nomorSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "Nomor surat");
        tanggalSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "Tanggal surat");
        action 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "Action");
    }

    private void populateData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        
        DateFormat formatter = new SimpleDateFormat();
        for (NomorTanggalWajibPajakWrapper obj : persiapanWrapper.getNomorTanggalWPList()) {
            Button aturBtn = new Button("Atur");
            
            dataCollection.add(new PersiapanNomorTanggalSuratPemberitahuanTableWrapper(
                    obj.getWajibPajak().getNpwpd(),
                    obj.getWajibPajak().getNamaWajibPajak(),
                    obj.getNomorPemberitahuanPemeriksaan(),
                    (obj.getTanggalPemberitahuanPemeriksaan()!= null)
                            ? formatter.format(obj.getTanggalPemberitahuanPemeriksaan())
                            : "",
                    aturBtn
            ));
            
        }
        for (final PersiapanNomorTanggalSuratPemberitahuanTableWrapper obj
                : dataCollection) {
            Button aturBtn = obj.getAction();
            
            aturBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("atur "+obj.getIdWP());
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("selected_wp_nomor_tanggal_input", obj.getIdWP());
                    //call form input nomor tanggal surat pemberitahuan
                    Pane formInputNomorTglSuratPemberitahuan = null;
                    try {
                        formInputNomorTglSuratPemberitahuan = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormInputSuratPemberitahuanNomorTanggal.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Form input nomor dan tanggal surat pemberitahuan");
                    stage.setScene(new Scene(formInputNomorTglSuratPemberitahuan));
                    stage.show();
                }
                
            });
        }
        
        
    }

    private void associateDataWithColumn() {
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPemberitahuanTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPemberitahuanTableWrapper, String>("namaWP"));
        nomorSurat.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPemberitahuanTableWrapper, String>("nomorSurat"));
        tanggalSurat.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPemberitahuanTableWrapper, String>("tanggalSurat"));
        action.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPemberitahuanTableWrapper, String>("Action"));
    }
    
}
