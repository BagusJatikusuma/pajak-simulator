/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaporan;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.NomorBerkasService;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaporanWrapper;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class DetailEvaluasiUIController implements Initializable {
    
    @FXML private Label tahapKeField;
    @FXML private Label nomorTanggalSPField;
    @FXML private Label namaTimLabel;
    @FXML private Label namaWPLabel;
    @FXML private Label npwpdLabel;
    
    @FXML private TextField temuanField;
    @FXML private TextField nomorSKPDField;
    @FXML private DatePicker tanggalSKPDField;
    
    @FXML private Button cancelBtn;
    private RekapitulasiService rekapitulasiService;
    private NomorBerkasService nomorBerkasService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        
        initLabel();
        initData();
        initFormat();
    }

    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void finishOperation() {
        PelaporanWrapper wrapper 
                = (PelaporanWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("pelaporan_wrapper");
        nomorBerkasService
                .setNomorTanggalSKPD(
                        wrapper.getSuratPerintahSelected().getIdSP(), 
                        wrapper.getWpSelected().getNpwpd(), 
                        ((nomorSKPDField.getText() != null))?nomorSKPDField.getText():null, 
                        (tanggalSKPDField.getValue()!=null)
                                ?String.valueOf(Date.from(tanggalSKPDField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime())
                                :null);
        wrapper.getWpSelected().getNomorBerkas().setNomorSKPD(nomorSKPDField.getText());
        if (tanggalSKPDField.getValue()!=null)
            wrapper.getWpSelected().getNomorBerkas().setTanggalSKPD(String.valueOf(Date.from(tanggalSKPDField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        
    }
    
    public void initLabel() {
        PelaporanWrapper wrapper 
                = (PelaporanWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("pelaporan_wrapper");
        
        tahapKeField.setText(String.valueOf(wrapper.getSuratPerintahSelected().getTahap()));
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        nomorTanggalSPField.setText(
                "800/"+wrapper.getSuratPerintahSelected().getNomorSurat()+"/Bapenda"
                +", "+formatter.format(new Date(Long.valueOf(wrapper.getSuratPerintahSelected().getTanggalSurat()))));
        namaTimLabel.setText(wrapper.getTimSPSelected().getNamaTim());
        namaWPLabel.setText(wrapper.getWpSelected().getNamaWajibPajak());
        npwpdLabel.setText(wrapper.getWpSelected().getNpwpd());
        
    }
    
    private void initData() {
        PelaporanWrapper wrapper 
                = (PelaporanWrapper) SessionProvider
                            .getGlobalSessionsMap()
                            .get("pelaporan_wrapper");
        RekapitulasiWrapper rekapWrapper
                = rekapitulasiService.getRekapitulasi(
                        wrapper.getSuratPerintahSelected().getIdSP(), 
                        wrapper.getWpSelected().getNpwpd());
        if (!rekapWrapper.getListRekapitulasi().isEmpty()) {
            rekapitulasiService.getTotalRekapitulasi(rekapWrapper);
            
            NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
            DecimalFormat formatter = (DecimalFormat) anotherFormat;
        
            temuanField.setText("Rp"+formatter.format(new BigDecimal(rekapWrapper.getTotalJumlah().doubleValue())));
        }
        else {
            temuanField.setText("Belum ada temuan");
        }
        
        nomorSKPDField.setText(wrapper.getWpSelected().getNomorBerkas().getNomorSKPD());
        if (wrapper.getWpSelected().getNomorBerkas().getTanggalSKPD() != null)
            tanggalSKPDField.setValue(
                    new Date(Long.valueOf(wrapper.getWpSelected().getNomorBerkas().getTanggalSKPD()))
                                .toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate());
        
    }
    
    public void initFormat() {
        temuanField.setEditable(false);
        
        tanggalSKPDField.setConverter(new StringConverter<LocalDate>()
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
    
}
