/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Surat;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.NomorBerkasService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormInputNomorTanggalPHPController implements Initializable {

    @FXML private TextField nomorSuratField;
    @FXML private DatePicker tanggalPengesahanField;
    
    @FXML private Button cancelBtn;
    private NomorBerkasService nomorBerkasService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        setDatePickerFormat();
        initData();
    }

    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void tambahOperation() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                    .getGlobalSessionsMap()
                    .get("pelaksanaan_wrapper");
        if (!nomorSuratField.getText().equals("")
                && tanggalPengesahanField.getValue() != null) {
            String suratIndexSelected = (String) SessionProvider.getGlobalSessionsMap().get("surat_selected");
            nomorBerkasService.setNomorBerkas(
                    pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                    pelaksanaanWrapper.getWpSelected().getNpwpd(), 
                    nomorSuratField.getText(), 
                    String.valueOf(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()), 
                    (suratIndexSelected.equals("3"))?Surat.HASIL:Surat.TEGURAN_PERTAMA);
            System.out.println("nomor berkas surat hasil berhasil disimpan");
            if (suratIndexSelected.equals("3")) {
                WajibPajak wpSelected = pelaksanaanWrapper.getWpSelected();
                wpSelected.getNomorBerkas().setNomorSuratHasil(nomorSuratField.getText());
                wpSelected.getNomorBerkas().setTanggalSuratHasil(
                    String.valueOf(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            } else {
                WajibPajak wpSelected = pelaksanaanWrapper.getWpSelected();
                wpSelected.getNomorBerkas().setNomorTeguran1(nomorSuratField.getText());
                wpSelected.getNomorBerkas().setTanggalTeguran1(
                    String.valueOf(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            }
            
            Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
            rootpaneFormPelaksanaan.getChildren().remove(0);

            Pane contentPane = null;
            try { 
                contentPane
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormSuratPelaksanaanUI.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpaneFormPelaksanaan.getChildren().add(contentPane);
            
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }
    
    private void setDatePickerFormat() {
        tanggalPengesahanField.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter
                    =DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.forLanguageTag("id-ID"));

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
       
    }
    
    private void initData() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                    .getGlobalSessionsMap()
                    .get("pelaksanaan_wrapper");
        String suratIndexSelected = (String) SessionProvider.getGlobalSessionsMap().get("surat_selected");
        
        if (suratIndexSelected.equals("3")) {
            if ((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil() != null) 
                    && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil() != null) {
                nomorSuratField.setText(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorSuratHasil());
                tanggalPengesahanField.setValue(
                        new Date(Long.valueOf(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalSuratHasil()))
                                .toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } else {
            if ((pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1() != null) 
                    && pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1() != null) {
                nomorSuratField.setText(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran1());
                tanggalPengesahanField.setValue(
                        new Date(Long.valueOf(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran1()))
                                .toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate());
            }
        }
    }
    
}
