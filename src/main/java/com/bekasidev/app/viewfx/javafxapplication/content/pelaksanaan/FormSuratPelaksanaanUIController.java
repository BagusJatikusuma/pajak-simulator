/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.DaftarSuratPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormSuratPelaksanaanUIController implements Initializable {
    @FXML private TableView daftarSuratTable;
    @FXML private TableColumn no;
    @FXML private TableColumn namaSurat;
    @FXML private TableColumn keterangan;
    
    private ObservableList<DaftarSuratPelaksanaanTableWrapper> dataCollection;
    private ReportService reportService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reportService = ServiceFactory.getReportService();
        populateData();
        associateDataWithColumn();
        daftarSuratTable.setItems(dataCollection);
    }    

    private void populateData() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaksanaan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        boolean phpSudahDiatur = false;
        boolean beritaAcaraSudahDiatur = false;
        boolean suratTeguran1SudahDiAtur = false;
        boolean suratTeguran2SudahDiatur = false;
        if ((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil() != null) 
                && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil() != null)
            phpSudahDiatur = true;
        if (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalBeritaAcara() != null)
            beritaAcaraSudahDiatur = true;
        if ((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1() != null)
                && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1() != null)
            suratTeguran1SudahDiAtur = true;
        if ((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran2() != null)
                && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran2() != null
                && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getJamTeguran2() != null
                && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTempatTeguran2() != null
                && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getHariTeguran2() != null) {
            suratTeguran2SudahDiatur = true;   
        }
        
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("1","Surat Pernyataan Data Benar",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("2","Tanda Terima Surat Pemberitahuan Hasil Pemeriksaan",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("3","Pemberitahuan Hasil Pemeriksaan",
//                (phpSudahDiatur)?"Sudah diatur":"Belum diatur"));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("4","Lampiran Pemberitahuan Hasil Pemeriksaan",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("5","Surat Persetujuan",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("6","Pernyataan Persetujuan Hasil Pemeriksaan",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("7","Berita Acara Pembahasan Akhir Hasil Pemeriksaan",
//                (beritaAcaraSudahDiatur)?"Tanggal sudah diatur":"Tanggal Belum diatur"));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("8","Surat Pernyataan Kesanggupan Membayar Pajak Kurang Bayar",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("9","Surat Pernyataan Akan Membayar",""));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("10","Surat Teguran 1",
//                (suratTeguran1SudahDiAtur)?"Sudah diatur":"Belum diatur"));
//        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("11","Surat Teguran 2",
//                (suratTeguran2SudahDiatur)?"Sudah diatur":"Belum diatur"));
        
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("1","Surat Pernyataan Data Benar",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("2","Tanda Terima Surat Pemberitahuan Hasil Pemeriksaan (SPHP)",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("3","Pemberitahuan Hasil Pemeriksaan",
                (phpSudahDiatur)?"Sudah diatur":"Belum diatur"));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("4","Surat Persetujuan",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("5","Pernyataan Persetujuan Hasil Pemeriksaan",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("6","Berita Acara Pembahasan Akhir Hasil Pemeriksaan",
                (beritaAcaraSudahDiatur)?"Tanggal sudah diatur":"Tanggal Belum diatur"));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("7","Surat Pernyataan Kesanggupan Membayar Pajak Kurang Bayar",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("8","Surat Pernyataan Akan Membayar",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("9","Surat Teguran 1",
                (suratTeguran1SudahDiAtur)?"Sudah diatur":"Belum diatur"));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("10","Surat Teguran 2",
                (suratTeguran2SudahDiatur)?"Sudah diatur":"Belum diatur"));
        
    }

    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("no"));
        namaSurat.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("namaSurat"));
        keterangan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("keterangan"));
    }
    
    public void aturNomorTanggalSurat() {
        if (daftarSuratTable.getSelectionModel().getSelectedItem() == null) {
            showErrorAturSuratNotif();
            return;
        } 
        DaftarSuratPelaksanaanTableWrapper wrapper 
                = (DaftarSuratPelaksanaanTableWrapper)daftarSuratTable.getSelectionModel().getSelectedItem();
        if (wrapper.getNo().equals("3") 
                || wrapper.getNo().equals("9")) {
            if (wrapper.getNo().equals("3")) {
                SessionProvider.getGlobalSessionsMap().put("surat_selected","3");
            } else {
                SessionProvider.getGlobalSessionsMap().put("surat_selected","9");
            }
            Pane formAturBulanRekapitulasi = null;
            try {
                formAturBulanRekapitulasi = FXMLLoader
                        .load(getClass().getClassLoader().getResource("fxml/FormInputNomorTanggalPHP.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = new Stage();
            stage.setTitle("Form Atur Nomor Tanggal");
            stage.setScene(new Scene(formAturBulanRekapitulasi));
            
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        }
        else if (wrapper.getNo().equals("6")) {
            Pane formAturBulanRekapitulasi = null;
            try {
                formAturBulanRekapitulasi = FXMLLoader
                        .load(getClass().getClassLoader().getResource("fxml/FormInputTanggalBeritaAcaraUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = new Stage();
            stage.setTitle("Form Atur Tanggal");
            stage.setScene(new Scene(formAturBulanRekapitulasi));
            
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        else if (wrapper.getNo().equals("10")) {
            Pane formAturBulanRekapitulasi = null;
            try {
                formAturBulanRekapitulasi = FXMLLoader
                        .load(getClass().getClassLoader().getResource("fxml/FormInputSuratTeguranDua.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = new Stage();
            stage.setTitle("Form Atur Tanggal");
            stage.setScene(new Scene(formAturBulanRekapitulasi));
            
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        else {
            showErrorAturSuratNotif();
            return;
        }
        
    }
    
    public void printSurat() {
        if (daftarSuratTable.getSelectionModel().getSelectedItem() == null) {
            showErrorNotif();
            return;
        }
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaksanaan_wrapper");
        DaftarSuratPelaksanaanTableWrapper wrapper
                = (DaftarSuratPelaksanaanTableWrapper) daftarSuratTable.getSelectionModel().getSelectedItem();
        switch (Integer.valueOf(wrapper.getNo()).intValue()) {
            case 1:
                createSuratPernyataan1(pelaksanaanWrapper);
//                reportService.createSuratPernyataan1(pelaksanaanWrapper);
                break;
            case 2: 
                createTandaTerimaSPHP2(pelaksanaanWrapper);
//                reportService.createTandaTerimaSPHP2(pelaksanaanWrapper);
                break;
            case 3:
                createSuratPemberitahuanHasilPemeriksaan3(pelaksanaanWrapper);
//                reportService.createSuratPemberitahuanHasilPemeriksaan3(
//                        pelaksanaanWrapper, 
//                        ServiceFactory.getSuratPerintahService().getTimSP(
//                            pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
//                            pelaksanaanWrapper.getTimSelected().getIdTim()));
                break;
            case 4:
                createSuratPersetujuan4(pelaksanaanWrapper);
//                reportService.createSuratPersetujuan4(pelaksanaanWrapper);
                break;
            case 5:
                createPernyataanPersetujuanHasilPemeriksaan5(pelaksanaanWrapper);
//                reportService.createPernyataanPersetujuanHasilPemeriksaan5(pelaksanaanWrapper);
                break;
            case 6:
                createBeritaAcara8(pelaksanaanWrapper);
//                reportService.createBeritaAcara8(pelaksanaanWrapper, 
//                ServiceFactory.getSuratPerintahService().getTimSP(
//                        pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
//                        pelaksanaanWrapper.getTimSelected().getIdTim()));
                break;
            case 7:
                createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(pelaksanaanWrapper);
//                reportService.createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(pelaksanaanWrapper);
                break;
            case 8: 
                createSuratPernyataan7(pelaksanaanWrapper);
//                reportService.createSuratPernyataan7(pelaksanaanWrapper);
                break;
            case 9:
                createSuratTeguran1(pelaksanaanWrapper);
//                reportService.createSuratTeguran1(pelaksanaanWrapper);
                break;
            case 10:
                createSuratTeguran2(pelaksanaanWrapper);
//                reportService.createSuratTeguran2(pelaksanaanWrapper);
                break;
        }
        
    }
    
    public void backToRekapitulasi() {
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
    
    private void createSuratPernyataan1(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratPernyataan1(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    
    private void createTandaTerimaSPHP2(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createTandaTerimaSPHP2(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createSuratPemberitahuanHasilPemeriksaan3(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratPemberitahuanHasilPemeriksaan3(
                        pelaksanaanWrapper, 
                        ServiceFactory.getSuratPerintahService().getTimSP(
                            pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                            pelaksanaanWrapper.getTimSelected().getIdTim()));
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createSuratPersetujuan4(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratPersetujuan4(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createPernyataanPersetujuanHasilPemeriksaan5(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createPernyataanPersetujuanHasilPemeriksaan5(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createBeritaAcara8(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createBeritaAcara8(pelaksanaanWrapper, 
                ServiceFactory.getSuratPerintahService().getTimSP(
                        pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                        pelaksanaanWrapper.getTimSelected().getIdTim()));
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createSuratPernyataan7(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratPernyataan7(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createSuratTeguran1(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratTeguran1(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    private void createSuratTeguran2(PelaksanaanWrapper pelaksanaanWrapper) {
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createSuratTeguran2(pelaksanaanWrapper);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    
    private Stage initLoadingScreen() {
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
        
        return stage;
    }
    
    private void showErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Anda belum memilih jenis surat");
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
    
    private void showErrorAturSuratNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "jenis surat tidak dapat diatur");
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
