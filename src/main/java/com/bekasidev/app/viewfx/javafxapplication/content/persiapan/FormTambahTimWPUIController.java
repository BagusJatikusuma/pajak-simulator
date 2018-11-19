/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanPilihWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Bayu Arafli
 */
public class FormTambahTimWPUIController implements Initializable {
    @FXML private TableView PersiapanPilihWPTable;
    private TableColumn pilih;
    private TableColumn idWP;
    private TableColumn namaWP;
    private TableColumn jenisWP;
    @FXML private ChoiceBox pilihPenanggungJawabField;
    @FXML private ChoiceBox pilihSupervisorField;
    @FXML private ChoiceBox pilihTimField;
    private ObservableList<PersiapanPilihWPTableWrapper> dataCollection;
    private WajibPajakService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFromFXML();
        populateData();
        associateDataWithColumn();
        PersiapanPilihWPTable.setItems(dataCollection);
    }
    
    public void tambahTimPemeriksaOperation() {
        
    }
    
    public void canceTambahTimPemeriksalOperation() {
        
    }
    
    private void addFromFXML() {
        pilih
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "pilih");
        idWP
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "Id WP");
        namaWP 
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "Nama WP");
        jenisWP 
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "Jenis WP");
    }
    
    private void populateData() {
        service = ServiceFactory.getWajibPajakService();
        List<WajibPajak> wajibPajaks = service.getAllWP();
        
        for (WajibPajak obj : wajibPajaks) {
            CheckBox checkBox = new CheckBox();
            String jenisWP = "";
            switch(obj.getJenisWp()) {
                case 0: 
                    jenisWP = "Restoran";
                    break;
                case 1: 
                    jenisWP = "Hotel";
                    break;
                case 2: 
                    jenisWP = "parkir";
                    break;
                case 3:
                    jenisWP = "Hiburan";
                    break;
                case 4: 
                    jenisWP = "Penerangan Jalan";
                    break;
                default:
                    jenisWP = "Unidentified";
            }
            dataCollection.add(new PersiapanPilihWPTableWrapper(
                    checkBox,
                    obj.getIdWajibPajak(),
                    obj.getNamaWajibPajak(),
                    jenisWP
            ));
        }
        
    }
    
    private void associateDataWithColumn() {
        pilih.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("pilih"));
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("namaWP"));
        jenisWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("jenisWP"));
    }
    
}
