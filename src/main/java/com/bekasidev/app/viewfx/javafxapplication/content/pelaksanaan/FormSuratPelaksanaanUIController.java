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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        
    }
    
    public void printSurat() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaksanaan_wrapper");
        DaftarSuratPelaksanaanTableWrapper wrapper
                = (DaftarSuratPelaksanaanTableWrapper) daftarSuratTable.getSelectionModel().getSelectedItem();
        switch (Integer.valueOf(wrapper.getNo()).intValue()) {
            case 1:
                reportService.createSuratPernyataan1(pelaksanaanWrapper);
                break;
            case 2: 
                reportService.createTandaTerimaSPHP2(pelaksanaanWrapper);
                break;
            case 3:
                reportService.createSuratPemberitahuanHasilPemeriksaan3(
                        pelaksanaanWrapper, 
                        ServiceFactory.getSuratPerintahService().getTimSP(
                            pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                            pelaksanaanWrapper.getTimSelected().getIdTim()));
                break;
            case 4:
                reportService.createSuratPersetujuan4(pelaksanaanWrapper);
                break;
            case 5:
                reportService.createPernyataanPersetujuanHasilPemeriksaan5(pelaksanaanWrapper);
                break;
            case 6:
                reportService.createBeritaAcara8(pelaksanaanWrapper, 
                ServiceFactory.getSuratPerintahService().getTimSP(
                        pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                        pelaksanaanWrapper.getTimSelected().getIdTim()));
                break;
            case 7:
                reportService.createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(pelaksanaanWrapper);
                break;
            case 8: 
                reportService.createSuratPernyataan7(pelaksanaanWrapper);
                break;
            case 9:
                reportService.createSuratTeguran1(pelaksanaanWrapper);
                break;
            case 10:
                System.out.println("not supported yet");
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
    
}
