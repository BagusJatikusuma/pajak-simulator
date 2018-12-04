/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanPilihWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private WajibPajakService wpService;
    private PegawaiService pegawaiService;
    
    @FXML private Button cancelTambahTimPemeriksaBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFromFXML();
        populateData();
        associateDataWithColumn();
        PersiapanPilihWPTable.setItems(dataCollection);
    }
    
    public void tambahTimPemeriksaOperation() {
        wpService = ServiceFactory.getWajibPajakService();
        
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        TimWPWrapper timWPWrapper
                = (TimWPWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("atur_tim_wp_selected");
        
        if (timWPWrapper == null) {
            System.out.println("tim wp wrapper is null");
            timWPWrapper = new TimWPWrapper();
        }
        
        timWPWrapper.setPenanggungJawab((Pegawai) pilihPenanggungJawabField.getSelectionModel().getSelectedItem());
        timWPWrapper.setSupervisor((Pegawai) pilihSupervisorField.getSelectionModel().getSelectedItem());
        timWPWrapper.setTim((Tim) pilihTimField.getSelectionModel().getSelectedItem());
        
        List<WajibPajak> wajibPajaksSelected = new ArrayList<>();
        ObservableList<PersiapanPilihWPTableWrapper> varList = PersiapanPilihWPTable.getItems(); 
        for (PersiapanPilihWPTableWrapper obj : varList) {
            if (obj.getPilih().isSelected()) {
                for (WajibPajak wp : wpService.getAllWP()) {
                    if (obj.getIdWP().equals(wp.getNpwpd())) {
                        wajibPajaksSelected.add(wp);
                        break;
                    }
                    
                }
                
            }
            
        }
        if (timWPWrapper != null) 
            timWPWrapper.setWajibPajaks(null);
        timWPWrapper.setWajibPajaks(wajibPajaksSelected);
        
        if ((TimWPWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("atur_tim_wp_selected") == null)
            persiapanWrapper.getTimWPWrappers().add(timWPWrapper);
        
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturTimWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);

        Stage stage = (Stage) cancelTambahTimPemeriksaBtn.getScene().getWindow();
        stage.close();
        
    }
    
    public void cancelTambahTimPemeriksalOperation() {
        System.out.println("cancel btn pressed");
        Stage stage = (Stage) cancelTambahTimPemeriksaBtn.getScene().getWindow();
        stage.close();
    }
    
    private void addFromFXML() {
        pilih
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "");
        idWP
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "ID WP");
        namaWP 
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "NAMA WAJIB PAJAK");
        jenisWP 
                = TableHelper.getTableColumnByName(PersiapanPilihWPTable, "JENIS WP");
    }
    
    private void populateData() {
        TimWPWrapper timWPWrapper
                = (TimWPWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("atur_tim_wp_selected");
        wpService = ServiceFactory.getWajibPajakService();
        List<WajibPajak> wajibPajaks = wpService.getAllWP();
        
        pegawaiService = ServiceFactory.getPegawaiService();
        List<Pegawai> pegawais = pegawaiService.getAllPegawai();
        
        ObservableList<Pegawai> calonPenanggungJawab = FXCollections.observableArrayList();
        ObservableList<Pegawai> calonSupervisor = FXCollections.observableArrayList();
        ObservableList<Tim> calonTims = FXCollections.observableArrayList();
        
        dataCollection = FXCollections.observableArrayList();
        
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
            
            if (timWPWrapper != null) {
                for (WajibPajak wp:timWPWrapper.getWajibPajaks()) {
                    if (obj.getNpwpd().equals(wp.getNpwpd())) {
                        checkBox.setSelected(true);
                        break;
                    }
                }
            }
            
            dataCollection.add(new PersiapanPilihWPTableWrapper(
                    checkBox,
                    obj.getNpwpd(),
                    obj.getNamaWajibPajak(),
                    jenisWP
            ));
        }
        
        for (Pegawai obj : pegawais) {
            calonPenanggungJawab.add(obj);
            calonSupervisor.add(obj);
        }
        List<Tim> tims = pegawaiService.getAllTim();
        for (Tim obj : tims) {
            calonTims.add(obj);
        }
        
        pilihPenanggungJawabField.setItems(calonPenanggungJawab);
        pilihSupervisorField.setItems(calonSupervisor);
        pilihTimField.setItems(calonTims);
        
        if (timWPWrapper != null) {
            for (Pegawai obj : pegawais) {
                if (obj.getNipPegawai().equals(timWPWrapper.getPenanggungJawab().getNipPegawai())) {
                    pilihPenanggungJawabField.getSelectionModel().select(obj);
                }
                if (obj.getNipPegawai().equals(timWPWrapper.getSupervisor().getNipPegawai())) {
                    pilihSupervisorField.getSelectionModel().select(obj);
                }
                
            }
            
            for (Tim obj : tims) {
                if (obj.getIdTim().equals(timWPWrapper.getTim().getIdTim())) {
                    pilihTimField.getSelectionModel().select(obj);
                }
            }
                        
        }
        
    }
    
    private void associateDataWithColumn() {
        pilih.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("pilih"));
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("namaWP"));
        jenisWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("jenisWP"));
    }
    
}
