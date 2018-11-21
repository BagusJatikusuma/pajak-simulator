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
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormPersiapanUIController implements Initializable {
    @FXML private Pane formDokumenPersiapanPane;
    
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
    @FXML private TextField lamaPelaksanaanField;
    
    @FXML private Button cancelBtn;
    private PegawaiService service;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComponentCollectorProvider
                .addFxComponent("root_form_persiapan_ui", formDokumenPersiapanPane);
        populateChoiceBox();
    }

    public void cancelOperation() {
        //remove session
        SessionProvider.getGlobalSessionsMap().remove("persiapan_wrapper");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void openFormAturTimWP() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        persiapanWrapper.setDasarNomor(dasarNomorField.getText());
        persiapanWrapper.setDasarTanggal(Date.from(dasarTanggalField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        persiapanWrapper.setDasarTahunAnggaran(dasarTahunAnggaranField.getText());
        
        Pegawai obj = (Pegawai)penandatanganField.getSelectionModel().getSelectedItem(); 
        System.out.println("penandatangan dipilih "+obj.getNamaPegawai());
        
        Pegawai pegawai = (Pegawai)penandatanganField.getSelectionModel().getSelectedItem();
        persiapanWrapper.setNama(pegawai.getNamaPegawai());
        persiapanWrapper.setJabatan(pegawai.getJabatan());
        
        persiapanWrapper.setPenandatangan(pegawai);
        persiapanWrapper.setMasaPajakAwalBulan(convertBulanStringIntoInteger(masaPajakAwalBulan.getSelectionModel().getSelectedItem().toString()));
        persiapanWrapper.setMasaPajakAkhirbulan(convertBulanStringIntoInteger(masaPajakAkhirBulan.getSelectionModel().getSelectedItem().toString()));
        persiapanWrapper.setMasaPajakAwalTahun(Integer.valueOf(masaPajakAwalTahun.getText()));
        persiapanWrapper.setMasaPajakAkhirTahun(Integer.valueOf(masaPajakAkhirTahun.getText()));
        
        persiapanWrapper.setTahapKe(Integer.valueOf(tahapKeField.getText()));
        persiapanWrapper.setBiayaTahunAPBD(Integer.valueOf(tahunAnggaranAPBDField.getText()));
        persiapanWrapper.setBiayaTanggalAPBD(Date.from(tanggalAPBDField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        persiapanWrapper.setBiayaNomorAPBD(nomorAPBDField.getText());
        
        persiapanWrapper.setLamaPelaksanaan(Integer.valueOf(lamaPelaksanaanField.getText()));
        
        persiapanWrapper.setDitetapkanDi(ditetapkanDiField.getText());
        persiapanWrapper.setTanggalPengesahan(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        persiapanWrapper.setNomorSurat(nomorSuratField.getText());
        
        //tampilan selanjutnya
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
        
    }
    
    public void populateChoiceBox() {
        service = ServiceFactory.getPegawaiService();
        
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        //jika membuka data dokumen persiapan yang sebelumnya pernah dibuat
        //indikator pernah membuat dokumen yaitu jika dasar tanggal tidak null
        if (persiapanWrapper.getDasarTanggal() != null) {            
            dasarNomorField.setText(persiapanWrapper.getDasarNomor());
            
            dasarTanggalField.setValue(persiapanWrapper
                .getDasarTanggal()
                .toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate());
            dasarTahunAnggaranField.setText(persiapanWrapper.getDasarTahunAnggaran());
            
//            penandatanganField.setValue(persiapanWrapper.getPenandatangan());
            ObservableList<Pegawai> ov = FXCollections.observableArrayList();
            for(Pegawai obj : service.getAllPegawai()){
                ov.add(obj);
            }
            penandatanganField.setItems(ov);
            int indexSavedInSession = 0;
            for (Iterator it = penandatanganField.getItems().iterator(); it.hasNext();) {
                Pegawai obj = (Pegawai) it.next();
                System.out.println("compare1 "+persiapanWrapper.getPenandatangan().getNamaPegawai());
                System.out.println("compare2 "+obj.getNamaPegawai());
                System.out.println("compare3 "+indexSavedInSession);
                if (obj.compareIdTo(persiapanWrapper.getPenandatangan())) {
                    break;
                }
                indexSavedInSession++;
            }
            
            penandatanganField.getSelectionModel().select(indexSavedInSession);
            masaPajakAwalBulan.setItems(
                    FXCollections.observableArrayList(
                            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                            "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
            masaPajakAkhirBulan.setItems(
                    FXCollections.observableArrayList(
                            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                            "Juli", "Agustus", "September", "Oktober", "November", "Desember"));
            masaPajakAwalBulan.getSelectionModel().select(persiapanWrapper.getMasaPajakAwalBulan().intValue());
            masaPajakAkhirBulan.getSelectionModel().select(persiapanWrapper.getMasaPajakAkhirbulan().intValue());
            
            
            masaPajakAwalTahun.setText(String.valueOf(persiapanWrapper.getMasaPajakAwalTahun()));
            masaPajakAkhirTahun.setText(String.valueOf(persiapanWrapper.getMasaPajakAkhirTahun()));
            
            tahapKeField.setText(String.valueOf(persiapanWrapper.getTahapKe()));
            tahunAnggaranAPBDField.setText(String.valueOf(persiapanWrapper.getBiayaTahunAPBD()));
            tanggalAPBDField.setValue(persiapanWrapper.getBiayaTanggalAPBD().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            nomorAPBDField.setText(persiapanWrapper.getBiayaNomorAPBD());
            
            ditetapkanDiField.setText(persiapanWrapper.getDitetapkanDi());
            tanggalPengesahanField.setValue(persiapanWrapper.getTanggalPengesahan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            nomorSuratField.setText(persiapanWrapper.getNomorSurat());
            
        }
        //jika pertama kali membuat dokumen
        else {
            ObservableList<Pegawai> ov = FXCollections.observableArrayList();
            for(Pegawai obj : service.getAllPegawai()){
                ov.add(obj);
            }
            penandatanganField.setItems(ov);
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
    
    private String convertBulanIntegerIntoString(Integer bulanInt) {
        switch(bulanInt) {
            case 0: return "Januari";
            case 1: return "Februari";
            case 2: return "Maret";
            case 3: return "April";
            case 4: return "Mei";
            case 5: return "Juni";
            case 6: return "Juli";
            case 7: return "Agustus";
            case 8: return "September";
            case 9: return "Oktober";
            case 10: return "November";
            case 11: return "Desember";
        }
        return "";
    }
    
    private Integer convertBulanStringIntoInteger(String bulanString) {
        switch(bulanString) {
            case "Januari" : return 0;
            case "Februari": return 1;
            case "Maret": return 2;
            case "April": return 3;
            case "Mei": return 4;
            case "Juni": return 5;
            case "Juli": return 6;
            case "Agustus": return 7;
            case "September": return 8;
            case "Oktober": return 9;
            case "November": return 10;
            case "Desember": return 11;
        }
        return null;
    }
}
