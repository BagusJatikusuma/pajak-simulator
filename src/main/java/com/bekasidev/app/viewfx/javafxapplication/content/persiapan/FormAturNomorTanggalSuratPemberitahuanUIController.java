/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Surat;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.NomorBerkasService;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.AnggotaDanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPemberitahuanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
    
    @FXML private Button backToFormSPBtn;
    
    private NomorBerkasService nomorBerkasService;
    private ReportService reportService;
    
    private ObservableList<PersiapanNomorTanggalSuratPemberitahuanTableWrapper> dataCollection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label headerLabel = (Label) SessionProvider.getGlobalSessionsMap()
                                            .get("header_form_persiapan");
        headerLabel.setText("FORM ATUR NOMOR & TANGGAL SURAT PEMBERITAHUAN");
        headerLabel.setLayoutX(110);
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        addFromFXML();
        populateData();
        associateDataWithColumn();
        AturNomorTanggalSuratPemberitahuanTable.setItems(dataCollection);
    }    
    
    public void simpanNomorTanggalSuratPemberitahuan() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        
        for (Iterator it = AturNomorTanggalSuratPemberitahuanTable.getItems().iterator(); it.hasNext();) {
            PersiapanNomorTanggalSuratPemberitahuanTableWrapper wrapper 
                    = (PersiapanNomorTanggalSuratPemberitahuanTableWrapper) it.next();
            
            System.out.println("nomor "+wrapper.getNomorSurat());
            System.out.println("tanggal "+wrapper.getTanggalSurat());
            
            try {
                nomorBerkasService.setNomorBerkas(
                        persiapanWrapper.getIdSP(),
                        wrapper.getIdWP(),
                        wrapper.getNomorSurat(),
                        String.valueOf(formatter.parse(wrapper.getTanggalSurat()).getTime()),
                        Surat.PEMBERITAHUAN);
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
        
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }
    
    public void backToFormSP() {
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturNomorTanggalSPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }
    
    public void printSuratPemberitahuan() {
        PersiapanNomorTanggalSuratPemberitahuanTableWrapper wrapper 
                = (PersiapanNomorTanggalSuratPemberitahuanTableWrapper) AturNomorTanggalSuratPemberitahuanTable.getSelectionModel().getSelectedItem();
        
        String npwpd = wrapper.getIdWP();
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        final PersiapanWrapper persiapanWrapper
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
        final NomorTanggalWajibPajakWrapper objNomorTanggalWPPenampungFinal = objNomorTanggalWPPenampung;
        
        TimWPWrapper objTimPenampung = null;        
        WajibPajak objWPPenampung = null;
        for(TimWPWrapper objTim : persiapanWrapper.getTimWPWrappers()){
            
            for(WajibPajak objWP : objTim.getWajibPajaks()){
                if (objWP.getNpwpd().equals(npwpd)) {
                    objTimPenampung = objTim;
                    objWPPenampung = objWP;
                    break;
                }
            }
        }
        final WajibPajak objWPPenampungFinal = objWPPenampung;
        final TimWPWrapper objTimPenampungFinal = objTimPenampung;
        
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoadingTest.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(contentPane));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createPemberitahuanPemeriksaanPerWP(
                        persiapanWrapper, 
                        objTimPenampungFinal, 
                        objNomorTanggalWPPenampungFinal, 
                        objWPPenampungFinal);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
        
//        reportService.createPemberitahuanPemeriksaanPerWP(persiapanWrapper, objTimPenampung, objNomorTanggalWPPenampung, objWPPenampung);
                
        System.out.println("Beres Surat Pemberitahuan Pemeriksa");
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
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "ID WP");
        namaWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "NAMA WAJIB PAJAK");
        nomorSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "NOMOR SURAT");
        tanggalSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "TANGGAL SURAT");
        action 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPemberitahuanTable, "ACTION");
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
                    
                    stage.initStyle(StageStyle.UTILITY);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
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
