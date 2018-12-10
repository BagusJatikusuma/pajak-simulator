/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PilihSPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PilihWajibPajakTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormPelaksanaanWPUIController implements Initializable {
    @FXML private TableView pilihWajibPajakTable;
    @FXML private TableColumn idWP;
    @FXML private TableColumn namaWP;
    @FXML private TableColumn jenisWP;
    
    private ObservableList<PilihWajibPajakTableWrapper> dataCollection;
    private Map<String, WajibPajak> wajibPajakMapper = new HashMap<>();
    
    @FXML private Button backBtn;
    private RekapitulasiService rekapitulasiService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        populateData();
        associateDataWithColumn();
        pilihWajibPajakTable.setItems(dataCollection);
    }

    public void backToPilihTim() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        if (pelaksanaanWrapper.getWpSelected() != null)
            pelaksanaanWrapper.setWpSelected(null);
        if (pelaksanaanWrapper.getRekapitulasiWrapper() != null)
            pelaksanaanWrapper.setRekapitulasiWrapper(null);
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanTimUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
    }
    
    public void openAturRekapitulasi() {
        if (pilihWajibPajakTable.getSelectionModel().getSelectedItem() == null) {
            showErrorNotif();
            return;
        }
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        PilihWajibPajakTableWrapper wrapper
                = (PilihWajibPajakTableWrapper) pilihWajibPajakTable.getSelectionModel().getSelectedItem();
        WajibPajak wp = wajibPajakMapper.get(wrapper.getIdWP());
        
        if (pelaksanaanWrapper.getRekapitulasiWrapper() != null) {
            if (!pelaksanaanWrapper.getWpSelected().getNpwpd()
                    .equals(wp.getNpwpd())) {
                pelaksanaanWrapper.setRekapitulasiWrapper(null);
            }
        }
        
        pelaksanaanWrapper.setWpSelected(wp);
        Map<String, RekapitulasiWrapper> rekapMapperHistory
                = (Map<String, RekapitulasiWrapper>) SessionProvider.getGlobalSessionsMap()
                        .get("rekap_wrapper_history");
        if (rekapMapperHistory != null) {
            pelaksanaanWrapper.setRekapitulasiWrapper(
                    rekapMapperHistory.get(
                            pelaksanaanWrapper.getPersiapanWrapper().getIdSP()
                            +pelaksanaanWrapper.getTimSelected().getIdTim()
                            +pelaksanaanWrapper.getWpSelected().getNpwpd()));
        }
        
        if (pelaksanaanWrapper.getRekapitulasiWrapper() == null) {
            RekapitulasiWrapper rekapitulasiWrapper
                    = new RekapitulasiWrapper();
            rekapitulasiWrapper.setIdSP(pelaksanaanWrapper.getPersiapanWrapper().getIdSP());
            rekapitulasiWrapper.setIdWP(pelaksanaanWrapper.getWpSelected().getNpwpd());
            rekapitulasiWrapper.setTotalDenda(Double.valueOf(0));
            rekapitulasiWrapper.setTotalJumlah(Double.valueOf(0));
            rekapitulasiWrapper.setTotalOmzet(Double.valueOf(0));
            rekapitulasiWrapper.setTotalOmzetLaporan(Double.valueOf(0));
            rekapitulasiWrapper.setTotalOmzetPeriksa(Double.valueOf(0));
            rekapitulasiWrapper.setTotalOmzetPeriksa(Double.valueOf(0));
            rekapitulasiWrapper.setTotalPajakDisetor(Double.valueOf(0));
            rekapitulasiWrapper.setTotalPajakPeriksa(Double.valueOf(0));
            rekapitulasiWrapper.setTotalPokokPajak(Double.valueOf(0));

            pelaksanaanWrapper.setRekapitulasiWrapper(rekapitulasiWrapper);
            rekapitulasiService.setBulanRekapitulasi(
                rekapitulasiWrapper, 
                convertDateNumberToDate(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan(),
                        pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun()), 
                convertDateNumberToDate(pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan(),
                        pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun()));
        }
        
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormDaftarRekapitulasiPerbandinganPendapatan.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
    }

    private void populateData() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        for (TimWPWrapper timWP : pelaksanaanWrapper.getPersiapanWrapper().getTimWPWrappers()) {
            if (timWP.getTim().getIdTim()
                    .equals(pelaksanaanWrapper.getTimSelected().getIdTim())) {
                for (WajibPajak wp : timWP.getWajibPajaks()) {
                    String jenisWP = "";
                    switch(wp.getJenisWp()) {
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
                    dataCollection.add(new PilihWajibPajakTableWrapper(
                            wp.getNpwpd(),
                            wp.getNamaWajibPajak(),
                            jenisWP
                    ));
                    wajibPajakMapper.put(wp.getNpwpd(), wp);
                }
                break;
            }
        }
    }

    private void associateDataWithColumn() {
        idWP.setCellValueFactory(new PropertyValueFactory<PilihWajibPajakTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PilihWajibPajakTableWrapper, String>("namaWP"));
        jenisWP.setCellValueFactory(new PropertyValueFactory<PilihWajibPajakTableWrapper, String>("jenisWP"));
    }
    
    private Date convertDateNumberToDate(int month, int year) {
        String masaPajakAwal = "01."+(month+1)+"."+year;
        System.out.println("masa pajak awal "+masaPajakAwal);
        DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        
        try {
            System.out.println("date "+dateFormatter.parse(masaPajakAwal).getTime());
            return dateFormatter.parse(masaPajakAwal);
        } catch (ParseException ex) {
            Logger.getLogger(FormPelaksanaanContentUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void showErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Anda belum memilih Wajib Pajak");
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
