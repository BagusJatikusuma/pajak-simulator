/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.NomorBerkasService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class FormInputSuratTeguranDuaController implements Initializable {

    @FXML private TextField nomorSuratField;
    @FXML private DatePicker tanggalPengesahanField;
    @FXML private DatePicker hariTanggalField;
    @FXML private TextField waktuField;
    @FXML private TextField tempatField;
    
    @FXML private Button cancelBtn;
    private NomorBerkasService nomorBerkasService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initData();
        initFormat();
    }    
    
    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void tambahOperation() {
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                    .getGlobalSessionsMap()
                    .get("pelaksanaan_wrapper");
        if (hariTanggalField.getValue() != null
                && !waktuField.getText().equals("")
                && !tempatField.getText().equals("")) {
            nomorBerkasService.setBerkasTeguran2(
                    pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                    pelaksanaanWrapper.getWpSelected().getNpwpd(), 
                    nomorSuratField.getText(),
                    (tanggalPengesahanField.getValue() == null)?
                            null:String.valueOf(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()), 
                    waktuField.getText(), 
                    tempatField.getText(), 
                    formatter.format(Date.from(hariTanggalField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            WajibPajak wpSelected = pelaksanaanWrapper.getWpSelected();
            wpSelected.getNomorBerkas().setNomorTeguran2(nomorSuratField.getText());
            if (tanggalPengesahanField.getValue() != null)
                wpSelected.getNomorBerkas().setTanggalTeguran2(String.valueOf(Date.from(tanggalPengesahanField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            wpSelected.getNomorBerkas().setHariTeguran2(
                    formatter.format(Date.from(hariTanggalField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            wpSelected.getNomorBerkas().setJamTeguran2(waktuField.getText());
            wpSelected.getNomorBerkas().setTempatTeguran2(tempatField.getText());
            
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
    
    private void initFormat() {
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
        hariTanggalField.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter
                    =DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy").withLocale(Locale.forLanguageTag("id-ID"));

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
        if (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran2() != null) {
            nomorSuratField.setText(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getNomorTeguran2());
        }
        if (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran2() != null) {
            tanggalPengesahanField.setValue(
                new Date(Long.valueOf(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTanggalTeguran2()))
                            .toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getJamTeguran2() != null) {
            waktuField.setText(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getJamTeguran2());
        }
        if (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTempatTeguran2() != null) {
            tempatField.setText(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getTempatTeguran2());
        }
        if (pelaksanaanWrapper.getWpSelected().getNomorBerkas().getHariTeguran2() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
            
//            hariTanggalField.setValue(
//                new Date(Long.valueOf(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getHariTeguran2()))
//                            .toInstant()
//                            .atZone(ZoneId.systemDefault()).toLocalDate());
            try {
                hariTanggalField.setValue(
                        formatter.parse(pelaksanaanWrapper.getWpSelected().getNomorBerkas().getHariTeguran2())
                                .toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate());
            } catch (ParseException ex) {
                Logger.getLogger(FormInputSuratTeguranDuaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
