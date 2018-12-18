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
import com.bekasidev.app.service.backend.BerkasPersiapanService;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import static com.bekasidev.app.view.util.ConverterHelper.convertBulanIntegerIntoString;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenPinjamanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanPilihWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML private TextField cariWPField;
    private ObservableList<PersiapanPilihWPTableWrapper> dataCollection;
    //digunakan sebagai penampung data hasil pencarian
    private ObservableList<PersiapanPilihWPTableWrapper> filteredCollection;
    private WajibPajakService wpService;
    private PegawaiService pegawaiService;
    private BerkasPersiapanService berkasPersiapanService;
    
    @FXML private Button cancelTambahTimPemeriksaBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filteredCollection = FXCollections.observableArrayList();
        addFromFXML();
        populateDataChoiceBox();
        populateData();
        associateDataWithColumn();
        PersiapanPilihWPTable.setItems(dataCollection);
    }
    
    public void cariWajibPajak() {
        if (!cariWPField.getText().equals("")) {
            if (!populateDataBasedSearch().isEmpty())
                PersiapanPilihWPTable.setItems(populateDataBasedSearch());
            PersiapanPilihWPTable.refresh();
        }
        
    }
    
    public void resetTable() {
//        populateData();
        //synchronkan dengan perubahan pada tabel hasil pencarian
        for (PersiapanPilihWPTableWrapper pwFilter : filteredCollection) {
            for (PersiapanPilihWPTableWrapper pw : dataCollection) {
                if (pw.getIdWP().equals(pwFilter.getIdWP())) {
                    pw.getPilih().setSelected(pwFilter.getPilih().isSelected());
                    break;
                }
            }
        }
        PersiapanPilihWPTable.setItems(dataCollection);
    }
    
    public void lihatDipilih() {
        List<PersiapanPilihWPTableWrapper> allWPselected = new ArrayList<>();
        
        for (PersiapanPilihWPTableWrapper pwFilter : filteredCollection) {
            for (PersiapanPilihWPTableWrapper pw : dataCollection) {
                if (pw.getIdWP().equals(pwFilter.getIdWP())) {
                    pw.getPilih().setSelected(pwFilter.getPilih().isSelected());
                    break;
                }
            }
        }
        
        for (PersiapanPilihWPTableWrapper pw : dataCollection) {
            if (pw.getPilih().isSelected())
                allWPselected.add(pw);
        }
        
        SessionProvider.getGlobalSessionsMap().put("tim_wp_dipilih", allWPselected);
        
        Pane popup = null;
        try {
            popup = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/WPTimDipilihUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("daftar wp yang dipilih");
        stage.setScene(new Scene(popup));
        
//        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }
    
    public void tambahTimPemeriksaOperation() {
        wpService = ServiceFactory.getWajibPajakService();
        
        //synchronkan dengan perubahan pada tabel hasil pencarian
        for (PersiapanPilihWPTableWrapper pwFilter : filteredCollection) {
            for (PersiapanPilihWPTableWrapper pw : dataCollection) {
                if (pw.getIdWP().equals(pwFilter.getIdWP())) {
                    pw.getPilih().setSelected(pwFilter.getPilih().isSelected());
                    break;
                }
            }
        }
        PersiapanPilihWPTable.setItems(dataCollection);
        
        if (!validateField()) {
            return;
        }
        
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
        List<WajibPajak> wpDB = wpService.getAllWP();
        ObservableList<PersiapanPilihWPTableWrapper> varList = PersiapanPilihWPTable.getItems(); 
        for (PersiapanPilihWPTableWrapper obj : varList) {
            if (obj.getPilih().isSelected()) {
                for (WajibPajak wp : wpDB) {
                    if (obj.getIdWP().equals(wp.getNpwpd())) {
                        wajibPajaksSelected.add(wp);
                        setDefaultDokumenPinjamanWP(wp, persiapanWrapper);
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
        rootpaneFormPersiapan.getChildren().remove(1*(rootpaneFormPersiapan.getChildren().size()-1));

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
        
//        pegawaiService = ServiceFactory.getPegawaiService();
//        List<Pegawai> pegawais = pegawaiService.getAllPegawai();
//        
//        ObservableList<Pegawai> calonPenanggungJawab = FXCollections.observableArrayList();
//        ObservableList<Pegawai> calonSupervisor = FXCollections.observableArrayList();
//        ObservableList<Tim> calonTims = FXCollections.observableArrayList();
        
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
        
//        for (Pegawai obj : pegawais) {
//            calonPenanggungJawab.add(obj);
//            calonSupervisor.add(obj);
//        }
//        List<Tim> tims = pegawaiService.getAllTim();
//        for (Tim obj : tims) {
//            calonTims.add(obj);
//        }
//        
//        pilihPenanggungJawabField.setItems(calonPenanggungJawab);
//        pilihSupervisorField.setItems(calonSupervisor);
//        pilihTimField.setItems(calonTims);
//        
//        if (timWPWrapper != null) {
//            for (Pegawai obj : pegawais) {
//                if (obj.getNipPegawai().equals(timWPWrapper.getPenanggungJawab().getNipPegawai())) {
//                    pilihPenanggungJawabField.getSelectionModel().select(obj);
//                }
//                if (obj.getNipPegawai().equals(timWPWrapper.getSupervisor().getNipPegawai())) {
//                    pilihSupervisorField.getSelectionModel().select(obj);
//                }
//                
//            }
//            
//            for (Tim obj : tims) {
//                if (obj.getIdTim().equals(timWPWrapper.getTim().getIdTim())) {
//                    pilihTimField.getSelectionModel().select(obj);
//                }
//            }
//                        
//        }
        
    }
    
    private void populateDataChoiceBox() {
        TimWPWrapper timWPWrapper
                = (TimWPWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("atur_tim_wp_selected");
        
        pegawaiService = ServiceFactory.getPegawaiService();
        List<Pegawai> pegawais = pegawaiService.getAllPegawai();
        
        ObservableList<Pegawai> calonPenanggungJawab = FXCollections.observableArrayList();
        ObservableList<Pegawai> calonSupervisor = FXCollections.observableArrayList();
        ObservableList<Tim> calonTims = FXCollections.observableArrayList();
        
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
    
    private ObservableList<PersiapanPilihWPTableWrapper> populateDataBasedSearch() {
        filteredCollection = FXCollections.observableArrayList();
        String searchText = cariWPField.getText().toLowerCase();
        
        for (Iterator it = dataCollection.iterator(); it.hasNext();) {
            PersiapanPilihWPTableWrapper wrapper = (PersiapanPilihWPTableWrapper) it.next();
            if (wrapper.getNamaWP().toLowerCase().contains(searchText)) {
                filteredCollection.add(wrapper);
            }
        }
        
        return filteredCollection;
    }
    
    private void associateDataWithColumn() {
        pilih.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("pilih"));
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("namaWP"));
        jenisWP.setCellValueFactory(new PropertyValueFactory<PersiapanPilihWPTableWrapper, String>("jenisWP"));
    }
    
    private boolean validateField() {
        if (pilihPenanggungJawabField.getSelectionModel().getSelectedItem() == null
                || pilihSupervisorField.getSelectionModel().getSelectedItem() == null
                || pilihTimField.getSelectionModel().getSelectedItem() == null) {
            SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "ada field yang belum diisi");
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
            
            return false;
        }
        return true;
    }
    
    private void setDefaultDokumenPinjamanWP(WajibPajak wp, PersiapanWrapper persiapanWrapper) {
        berkasPersiapanService = ServiceFactory.getBerkasPersiapanService();
        System.out.println("set default dokumen pinjaman wp "+wp.getNamaWajibPajak());
        boolean isExist = false;
        for (DokumenPinjamanWajibPajakWrapper wrapper 
                : persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()) {
            
            if (wrapper.getWajibPajak().getNpwpd()
                    .equals(wp.getNpwpd())) {
                isExist = true;
                break;
            }
            
        }
        if (!isExist) {
            setDokumen(wp, persiapanWrapper);
        }
        
    }
    
    private void setDokumen(WajibPajak wp, PersiapanWrapper persiapanWrapper) {
        if (wp.getListPinjaman().isEmpty()) {
            System.out.println("start default dokumen pinjaman wp "+wp.getNamaWajibPajak());
            String masaPajakAwal 
                    = ConverterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAwalBulan())
                        + " "
                        +persiapanWrapper.getMasaPajakAwalTahun();
            String masaPajakAkhir
                    = ConverterHelper.convertBulanIntegerIntoString(persiapanWrapper.getMasaPajakAkhirbulan())
                        + " "
                        +persiapanWrapper.getMasaPajakAkhirTahun();
            berkasPersiapanService
                    .getDokumenPinjaman(wp, masaPajakAwal, masaPajakAkhir);
            persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()
                    .add(new DokumenPinjamanWajibPajakWrapper(wp, wp.getListPinjaman()));
        }
    }
    
}
