/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.DaftarSuratPelaksanaanTableWrapper;
import java.net.URL;
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
        dataCollection = FXCollections.observableArrayList();
        
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("1","Surat Pernyataan Data Benar",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("2","Tanda Terima Surat Pemberitahuan Hasil Pemeriksaan",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("3","Pemberitahuan Hasil Pemeriksaan","Belum diatur"));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("4","Lampiran Pemberitahuan Hasil Pemeriksaan",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("5","Surat Persetujuan",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("6","Pernyataan Persetujuan Hasil Pemeriksaan",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("7","Berita Acara Pembahasan Akhir Hasil Pemeriksaan","Tanggal Belum diatur"));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("8","Surat Pernyataan Kesanggupan Membayar Pajak Kurang Bayar",""));
        dataCollection.add(new DaftarSuratPelaksanaanTableWrapper("9","Surat Pernyataan Akan Membayar",""));
        
    }

    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("no"));
        namaSurat.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("namaSurat"));
        keterangan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("keterangan"));
    }
    
    public void aturNomorTanggalSurat() {
        
    }
    
    public void printSurat() {
        DaftarSuratPelaksanaanTableWrapper wrapper
                = (DaftarSuratPelaksanaanTableWrapper) daftarSuratTable.getSelectionModel().getSelectedItem();
        switch (Integer.valueOf(wrapper.getNo()).intValue()) {
            case 1:
                
                break;
            case 2: 
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            case 7:
                
                break;
            case 8: 
                
                break;
            case 9:
                
                break;
        }
    }
    
    public void backToRekapitulasi() {
        
    }
    
}
