/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Surat;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.NomorBerkasService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.AnggotaDanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPemberitahuanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPeminjamanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapperJasper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapperJasper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturNomorTanggalSuratPeminjamanUIController implements Initializable {
    @FXML private TableView AturNomorTanggalSuratPeminjamanTable;
    private TableColumn idWP;
    private TableColumn namaWP;
    private TableColumn nomorSurat;
    private TableColumn tanggalSurat;
    private TableColumn action;
    @FXML private Button backToFormPemberitahuanBtn;
    @FXML private Button cancelBtn;
    
    private NomorBerkasService nomorBerkasService;
    private ReportService reportService;
    
    private ObservableList<PersiapanNomorTanggalSuratPeminjamanTableWrapper> dataCollection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label headerLabel = (Label) SessionProvider.getGlobalSessionsMap()
                                            .get("header_form_persiapan");
        headerLabel.setText("FORM ATUR NOMOR & TANGGAL SURAT PEMINJAMAN");
        headerLabel.setLayoutX(120);
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        addFromFXML();
        populateData();
        associateDataWithColumn();
        AturNomorTanggalSuratPeminjamanTable.setItems(dataCollection);
    }    
    
    public void backToFormPemberitahuan() {
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturNomorTanggalSuratPemberitahuanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }
    
    public void cancelOperation() {
        //remove session
        SessionProvider.getGlobalSessionsMap().remove("persiapan_wrapper");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void simpanNomorTanggalSuratPeminjaman() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        
        for (Iterator it = AturNomorTanggalSuratPeminjamanTable.getItems().iterator(); it.hasNext();) {
            PersiapanNomorTanggalSuratPeminjamanTableWrapper wrapper 
                    = (PersiapanNomorTanggalSuratPeminjamanTableWrapper) it.next();
            
            System.out.println("nomor "+wrapper.getNomorSurat());
            System.out.println("tanggal "+wrapper.getTanggalSurat());
            
            try {
                nomorBerkasService.setNomorBerkas(
                        persiapanWrapper.getIdSP(),
                        wrapper.getIdWP(),
                        wrapper.getNomorSurat(),
                        String.valueOf(formatter.parse(wrapper.getTanggalSurat()).getTime()),
                        Surat.PEMINJAMAN);
            } catch (ParseException ex) {
                Logger.getLogger(FormAturNomorTanggalSuratPemberitahuanUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }
    
    public void printQuesioner() {
        PersiapanNomorTanggalSuratPeminjamanTableWrapper wrapper 
                = (PersiapanNomorTanggalSuratPeminjamanTableWrapper) AturNomorTanggalSuratPeminjamanTable.getSelectionModel().getSelectedItem();
        
        String npwpd = wrapper.getIdWP();
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        int penampungJenisWP = 0;
        for(TimWPWrapper objTim : persiapanWrapper.getTimWPWrappers()){
            
            for(WajibPajak objWP : objTim.getWajibPajaks()){
                if (objWP.getNpwpd().equals(npwpd)) {
                    penampungJenisWP = objWP.getJenisWp();
                    break;
                }
            }
        }
        
        switch(penampungJenisWP){
            case 0:
                reportService.createQuesionerRestoran();
                break;
            case 1:
                reportService.createQuesionerHotel();
                break;
            case 2:
                reportService.createQuesionerParkir();
                break;
        }
    }
    
    public void printSuratPeminjaman() {
        PersiapanNomorTanggalSuratPeminjamanTableWrapper wrapper 
                = (PersiapanNomorTanggalSuratPeminjamanTableWrapper) AturNomorTanggalSuratPeminjamanTable.getSelectionModel().getSelectedItem();
        
        String npwpd = wrapper.getIdWP();
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        NomorTanggalWajibPajakWrapper objNomorTanggalWPPenampung = null;
        for(NomorTanggalWajibPajakWrapper obj : persiapanWrapper.getNomorTanggalWPList()){
            if (obj.getWajibPajak().getNpwpd().equals(npwpd)) {
                objNomorTanggalWPPenampung = obj;
                break;
            }
        }
        
        TimWPWrapper objTimPenampung = null;
        WajibPajak objWPPenampung = null;
        int penampungJenisWP = 0;
        for(TimWPWrapper objTim : persiapanWrapper.getTimWPWrappers()){
            
            for(WajibPajak objWP : objTim.getWajibPajaks()){
                if (objWP.getNpwpd().equals(npwpd)) {
                    objTimPenampung = objTim;
                    objWPPenampung = objWP;
                    penampungJenisWP = objWP.getJenisWp();
                    break;
                }
            }
        }
        
        reportService.createPersiapanPeminjamanBukuPerWP(persiapanWrapper, objTimPenampung, objNomorTanggalWPPenampung, objWPPenampung);
        
//        switch(penampungJenisWP){
//            case 0:
//                reportService.createPersiapanPeminjamanBukuPerWP(WP.RESTORAN, persiapanWrapper, objTimPenampung, objNomorTanggalWPPenampung, objWPPenampung);
//                break;
//            case 1:
//                reportService.createPersiapanPeminjamanBukuPerWP(WP.HOTEL, persiapanWrapper, objTimPenampung, objNomorTanggalWPPenampung, objWPPenampung);
//                break;
//            case 2:
//                reportService.createPersiapanPeminjamanBukuPerWP(WP.PARKIRAN, persiapanWrapper, objTimPenampung, objNomorTanggalWPPenampung, objWPPenampung);
//                break;
//        }
        System.out.println("Beres Surat Peminjaman Buku");
            
        
    }
    
    public void aturDokumenPinjaman() {
        PersiapanNomorTanggalSuratPeminjamanTableWrapper wrapper 
                = (PersiapanNomorTanggalSuratPeminjamanTableWrapper) AturNomorTanggalSuratPeminjamanTable.getSelectionModel().getSelectedItem();
        
        System.out.println("atur "+wrapper.getNamaWP());
        SessionProvider
                .getGlobalSessionsMap()
                .put("selected_dokumen_wp", wrapper.getIdWP());

        List<DokumenPinjaman> dokumenTempList = new ArrayList<>();
        SessionProvider
            .getGlobalSessionsMap()
            .put("dokumen_temp_list",dokumenTempList);

        Pane formAturDokumenWP = null;
        try {
            formAturDokumenWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormAturDokumenWP.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        SessionProvider
                .getGlobalSessionsMap()
                .put("stage_atur_dokumen_wp", stage);
        stage.setTitle("Form Atur Dokumen Wajib Pajak");
        stage.setScene(new Scene(formAturDokumenWP));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void addFromFXML() {
        idWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "ID WP");
        namaWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "NAMA WAJIB PAJAK");
        nomorSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "NOMOR SURAT");
        tanggalSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "TANGGAL SURAT");
        action 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "ACTION");
    }

    private void populateData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        for (NomorTanggalWajibPajakWrapper obj : persiapanWrapper.getNomorTanggalWPList()) {
            Button aturBtn = new Button("Atur");
            
            dataCollection.add(new PersiapanNomorTanggalSuratPeminjamanTableWrapper(
                    obj.getWajibPajak().getNpwpd(),
                    obj.getWajibPajak().getNamaWajibPajak(),
                    obj.getNomorPeminjamanDokumen(),
                    (obj.getTanggalPeminjamanDokumen()!= null)
                            ?formatter.format(obj.getTanggalPeminjamanDokumen())
                            :"",
                    aturBtn
            ));
            
        }
        for (final PersiapanNomorTanggalSuratPeminjamanTableWrapper obj
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
                    Pane formInputNomorTglSuratPeminjaman = null;
                    try {
                        formInputNomorTglSuratPeminjaman = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormInputSuratPeminjamanNomorTanggal.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Form input nomor dan tanggal surat peminjaman");
                    stage.setScene(new Scene(formInputNomorTglSuratPeminjaman));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }
                
            });
        }
    }

    private void associateDataWithColumn() {
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("namaWP"));
        nomorSurat.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("nomorSurat"));
        tanggalSurat.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("tanggalSurat"));
        action.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("Action"));
    }
    
}
