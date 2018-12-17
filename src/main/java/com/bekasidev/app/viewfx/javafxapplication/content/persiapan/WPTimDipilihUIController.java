/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanPilihWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class WPTimDipilihUIController implements Initializable {

    @FXML private TableView PersiapanWPTable;
    
    @FXML private TableColumn idWP;
    @FXML private TableColumn namaWP;
    @FXML private TableColumn jenisWP;
    
    private ObservableList<PersiapanWPTableWrapper> dataCollection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        PersiapanWPTable.setItems(dataCollection);
    }    

    private void addFromFXML() {
        idWP
                = TableHelper.getTableColumnByName(PersiapanWPTable, "ID WP");
        namaWP 
                = TableHelper.getTableColumnByName(PersiapanWPTable, "NAMA WAJIB PAJAK");
        jenisWP 
                = TableHelper.getTableColumnByName(PersiapanWPTable, "JENIS WP");
    }

    private void populateData() {
        dataCollection = FXCollections.observableArrayList();
        List<PersiapanPilihWPTableWrapper> allWPSelected
                = (List<PersiapanPilihWPTableWrapper>) SessionProvider.getGlobalSessionsMap().get("tim_wp_dipilih");
        for (PersiapanPilihWPTableWrapper wrapper : allWPSelected) {
            dataCollection.add(
                    new PersiapanWPTableWrapper(wrapper.getIdWP(), wrapper.getNamaWP(), wrapper.getJenisWP()));
        }
        
    }

    private void associateDataWithColumn() {
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanWPTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanWPTableWrapper, String>("namaWP"));
        jenisWP.setCellValueFactory(new PropertyValueFactory<PersiapanWPTableWrapper, String>("jenisWP"));
    }
    
}
