/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormPersiapanUIController implements Initializable {
    @FXML private TextField dasarNomorField;
    @FXML private DatePicker dasarTanggalField;
    @FXML private TextField dasarTahunAnggaranField;
    
    @FXML private ChoiceBox penandatanganField;
    @FXML private ChoiceBox masaPajakAwalBulan;
    @FXML private TextField masaPajakAwalTahun;
    @FXML private ChoiceBox masaPajakAkhirBulan;
    @FXML private TextField masaPajakAkhirTahun;
    
    @FXML private TextField tahapKeField;
    @FXML private TextField tahunAnggaranAPBDField;
    @FXML private DatePicker tanggalAPBDField;
    @FXML private TextField nomorAPBDField;
    @FXML private TextField ditetapkanDiField;
    @FXML private DatePicker tanggalPengesahanField;
    @FXML private TextField nomorSuratField;
    
    @FXML private Button cancelBtn;
    private PegawaiService service;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateChoiceBox();
    }

    public void cancelOperation() {
        
    }
    
    public void openFormAturTimWP() {
        
    }
    
    public void populateChoiceBox() {
//        ObservableList<Tim> ov = FXCollections.observableArrayList();
//        for(Tim tim : ServiceFactory.getPegawaiService().getAllTim()){
//            ov.add(tim);
//        }
        service = ServiceFactory.getPegawaiService();
        ObservableList<Pegawai> ov = FXCollections.observableArrayList();
        for(Pegawai obj : service.getAllPegawai()){
            ov.add(obj);
        }
        
        penandatanganField.setItems(ov);
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        dasarNomorField.setText(persiapanWrapper.getDasarNomor());
        
        if (persiapanWrapper.getDasarTanggal() != null) {
            LocalDate date = null;
            persiapanWrapper
                .getDasarTanggal()
                .toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
            dasarTanggalField.setValue(date);
        }
        
        dasarTahunAnggaranField.setText(persiapanWrapper.getDasarTahunAnggaran());
        
        masaPajakAwalBulan.setItems(
                FXCollections.observableArrayList(
                        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                        "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
        masaPajakAkhirBulan.setItems(
                FXCollections.observableArrayList(
                        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                        "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
        
    }
}
