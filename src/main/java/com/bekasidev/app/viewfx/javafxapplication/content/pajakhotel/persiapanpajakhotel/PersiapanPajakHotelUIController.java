/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pajakhotel.persiapanpajakhotel;

import com.bekasidev.app.viewfx.javafxapplication.content.pajakrestoran.persiapanpajakrestoran.*;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.TimPemeriksaModelView;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PersiapanPajakHotelUIController implements Initializable {
    @FXML private TextField nomorUrutSuratField;
    @FXML private ChoiceBox suratPerintahDikeluarkanOlehField;
    @FXML private TextField nomorUrutSuratPerintahField;
    @FXML private DatePicker tanggalSuratPerintahDikeluarkanField;
    @FXML private ChoiceBox timPemeriksaField;
    @FXML private ChoiceBox masaPajakAwalBulan;
    @FXML private ChoiceBox masaPajakAkhirBulan;
    @FXML private TextField masaPajakAwalTahun;
    @FXML private TextField masaPajakAkhirTahun;
    @FXML private TextField lamaPemeriksaanField;
    @FXML private ChoiceBox penandatanganField;
    
    @FXML private Button cancelBtn;
    
    ReportService reportService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        normalizeTextField();
        populateChoiceBox();
    }
    
    public void cekAnggotaTim(ActionEvent actionEvent) {
        System.out.println("cek anggota tim btn pressed");
    }
    
    public void cancelOperation(ActionEvent actionEvent) {
        System.out.println("cancel btn pressed");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void generateDocuments(ActionEvent actionEvent) {
        System.out.println("generate documents btn pressed");
        
        reportService
                = ServiceFactory.getReportService();
        
        PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_hotel");
        LocalDate localDate = tanggalSuratPerintahDikeluarkanField.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM YYYY");
        System.out.println(dateFormat.format(date));
        
        persiapanPajakPOJO.setSpDari(suratPerintahDikeluarkanOlehField.getSelectionModel().getSelectedItem().toString());
        persiapanPajakPOJO.setNomorSP(nomorUrutSuratPerintahField.getText());
        persiapanPajakPOJO.setJenisPajak("Hotel");
        persiapanPajakPOJO.setNomorUrutSurat(nomorUrutSuratField.getText());
        persiapanPajakPOJO.setTanggalTurunSP(dateFormat.format(date));        
        persiapanPajakPOJO.setLamaPemeriksaan(lamaPemeriksaanField.getText());        
        persiapanPajakPOJO.setNipPenandatangan(penandatanganField.getSelectionModel().getSelectedItem().toString());
        persiapanPajakPOJO.setMasaPajakBulanAwal(masaPajakAwalBulan.getSelectionModel().getSelectedItem().toString());        
        persiapanPajakPOJO.setMasaPajakTahunAwal(masaPajakAwalTahun.getText());        
        persiapanPajakPOJO.setMasaPajakBulanAkhir(masaPajakAkhirBulan.getSelectionModel().getSelectedItem().toString());        
        persiapanPajakPOJO.setMasaPajakTahunAkhir(masaPajakAkhirTahun.getText());
        Tim tim = (Tim) timPemeriksaField.getSelectionModel().getSelectedItem();
        TimPemeriksaModelView timPemeriksa = new TimPemeriksaModelView();
        timPemeriksa.setNamaTim(tim.getNamaTim());
        timPemeriksa.setAnggotaTims(ServiceFactory.getPegawaiService().getPegawaiByTim(tim.getIdTim()));
        persiapanPajakPOJO.setTimPemeriksa(timPemeriksa);
//        System.out.println(tim.getIdTim());
        
        reportService.createPersiapanPajakHotelReport();
        reportService.createPersiapanPajakHotelReport1();
        reportService.createPersiapanDokumenPinjamanHotel();
        test();
    }
    
    private void normalizeTextField() {
        nomorUrutSuratField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    nomorUrutSuratField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
//        nomorUrutSuratPerintahField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    nomorUrutSuratPerintahField.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
        masaPajakAwalTahun.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    masaPajakAwalTahun.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        masaPajakAkhirTahun.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    masaPajakAkhirTahun.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lamaPemeriksaanField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    lamaPemeriksaanField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    
    public void populateChoiceBox() {
        ObservableList<Tim> ov = FXCollections.observableArrayList();
        for(Tim tim : ServiceFactory.getPegawaiService().getAllTim()){
            ov.add(tim);
        }
        suratPerintahDikeluarkanOlehField.setItems(
                FXCollections.observableArrayList("Kepala Badan Pendapatan", "Sekretaris"));
        timPemeriksaField.setItems(ov);
        masaPajakAwalBulan.setItems(
                FXCollections.observableArrayList(
                        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                        "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
        masaPajakAkhirBulan.setItems(
                FXCollections.observableArrayList(
                        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                        "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
        penandatanganField.setItems(
                FXCollections.observableArrayList("Kepala Badan Pendapatan", "Sekretaris"));
    }
    
    private void test() {
        PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_restoran");
        
        System.out.println(persiapanPajakPOJO.getWajibPajak().getNpwpd());
        System.out.println(persiapanPajakPOJO.getWajibPajak().getNamaWP());
        System.out.println(persiapanPajakPOJO.getWajibPajak().getJenisWP());
        System.out.println(persiapanPajakPOJO.getWajibPajak().getAlamatWP());
        System.out.println(persiapanPajakPOJO.getWajibPajak().getDesaWP());
        System.out.println(persiapanPajakPOJO.getWajibPajak().getKecamatanWP());
        System.out.println(persiapanPajakPOJO.getSpDari());
        System.out.println(persiapanPajakPOJO.getNomorSP());
        System.out.println(persiapanPajakPOJO.getJenisPajak());
        System.out.println(persiapanPajakPOJO.getNomorUrutSurat());
        System.out.println(persiapanPajakPOJO.getTanggalTurunSP());
        System.out.println(persiapanPajakPOJO.getLamaPemeriksaan());
        System.out.println(persiapanPajakPOJO.getNipPenandatangan());
        System.out.println(persiapanPajakPOJO.getMasaPajakBulanAwal());
        System.out.println(persiapanPajakPOJO.getMasaPajakTahunAwal());
        System.out.println(persiapanPajakPOJO.getMasaPajakBulanAkhir());
        System.out.println(persiapanPajakPOJO.getMasaPajakTahunAkhir());
    }
}
