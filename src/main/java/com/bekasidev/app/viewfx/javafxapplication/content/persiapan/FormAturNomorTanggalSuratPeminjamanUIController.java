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
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.AnggotaDanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPemberitahuanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPeminjamanTableWrapper;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturNomorTanggalSuratPeminjamanUIController implements Initializable {
    @FXML private TableView AturNomorTanggalSuratPeminjamanTable;
    private TableColumn idWP;
    private TableColumn namaWP;
    private TableColumn nomorSurat;
    private TableColumn tanggalSurat;
    private TableColumn action;
    @FXML private Button cancelBtn;
    
    private NomorBerkasService nomorBerkasService;
    private ReportService reportService;
    
    private ObservableList<PersiapanNomorTanggalSuratPeminjamanTableWrapper> dataCollection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        addFromFXML();
        populateData();
        associateDataWithColumn();
        AturNomorTanggalSuratPeminjamanTable.setItems(dataCollection);
    }    
    
    public void cancelOperation() {
        
    }
    
    public void simpanNomorTanggalSuratPeminjaman() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider.getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        
        for (Iterator it = AturNomorTanggalSuratPeminjamanTable.getItems().iterator(); it.hasNext();) {
            PersiapanNomorTanggalSuratPeminjamanTableWrapper wrapper 
                    = (PersiapanNomorTanggalSuratPeminjamanTableWrapper) it.next();
            
            System.out.println("nomor "+wrapper.getNomorSurat());
            System.out.println("tanggal "+wrapper.getTanggalSurat());
            
            try {
                nomorBerkasService.setNomorBerkas(
                        persiapanWrapper.getIdSP(),
                        wrapper.getIdWP(),
                        wrapper.getNomorSurat(),
                        String.valueOf(formatter.parse(wrapper.getTanggalSurat()).getTime()),
                        Surat.PEMINJAMAN);
            } catch (ParseException ex) {
                Logger.getLogger(FormAturNomorTanggalSuratPemberitahuanUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void printSuratPeminjaman() {
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        //data dummi
            PersiapanWrapperJasper dummi = new PersiapanWrapperJasper();

            dummi.setNomorSurat(persiapanWrapper.getNomorSurat());
            dummi.setTanggalPengesahan(persiapanWrapper.getTanggalPengesahan());
            dummi.setDasarNomor(persiapanWrapper.getDasarNomor());
            dummi.setDasarTanggal(persiapanWrapper.getDasarTanggal());
            dummi.setDasarTahunAnggaran(persiapanWrapper.getDasarTahunAnggaran());
            dummi.setNama(persiapanWrapper.getNama());
            dummi.setJabatan(persiapanWrapper.getJabatan());

            dummi.setMasaPajakAwalBulan(persiapanWrapper.getMasaPajakAwalBulan());
            dummi.setMasaPajakAwalTahun(persiapanWrapper.getMasaPajakAwalTahun());
            dummi.setMasaPajakAkhirbulan(persiapanWrapper.getMasaPajakAkhirbulan());
            dummi.setMasaPajakAkhirTahun(persiapanWrapper.getMasaPajakAkhirTahun());
            dummi.setTahapKe(persiapanWrapper.getTahapKe());

            dummi.setLamaPelaksanaan(persiapanWrapper.getLamaPelaksanaan());
            dummi.setBiayaNomorAPBD(persiapanWrapper.getBiayaNomorAPBD());
            dummi.setBiayaTahunAPBD(persiapanWrapper.getBiayaTahunAPBD());
            dummi.setBiayaTanggalAPBD(persiapanWrapper.getBiayaTanggalAPBD());

            dummi.setDitetapkanDi(persiapanWrapper.getDitetapkanDi());

            dummi.setPenandatangan(persiapanWrapper.getPenandatangan());

            PegawaiService pegawaiService = ServiceFactory.getPegawaiService();

            for (TimWPWrapper tim : persiapanWrapper.getTimWPWrappers()) {

                List<Pegawai> anggotaTimList = pegawaiService.getPegawaiByTim(tim.getTim().getIdTim());
                List<AnggotaDanWajibPajakWrapper> wajibPajakList = new ArrayList<AnggotaDanWajibPajakWrapper>();

                int jumlah = anggotaTimList.size();
                if(jumlah < tim.getWajibPajaks().size()){
                    jumlah = tim.getWajibPajaks().size();
                }

                for (int i = 0; i < jumlah; i++) {
                    AnggotaDanWajibPajakWrapper wp = new AnggotaDanWajibPajakWrapper();

                    if(i < tim.getWajibPajaks().size()){
                        wp.setIdWajibPajak(tim.getWajibPajaks().get(i).getNpwpd());
                        wp.setNamaWajibPajak(tim.getWajibPajaks().get(i).getNamaWajibPajak());
                        wp.setJenisWp(tim.getWajibPajaks().get(i).getJenisWp());
                    } else {
                        wp.setIdWajibPajak("");
                        wp.setNamaWajibPajak("");
                        wp.setJenisWp((short) -1);
                    }

                    if (i < anggotaTimList.size()) {
                        wp.setIdTim(anggotaTimList.get(i).getIdTim());
                        wp.setNipPegawai(anggotaTimList.get(i).getNipPegawai());
                        wp.setNamaPegawai(anggotaTimList.get(i).getNamaPegawai());
                        wp.setPangkat(anggotaTimList.get(i).getPangkat());
                        wp.setGolongan(anggotaTimList.get(i).getGolongan());
                        wp.setJabatanTim(anggotaTimList.get(i).getJabatanTim());
                    } else {
                        wp.setIdTim("");
                        wp.setNipPegawai("");
                        wp.setNamaPegawai("");
                        wp.setPangkat("");
                        wp.setGolongan("");
                    }

                    wajibPajakList.add(wp);
                }

                TimWPWrapperJasper objTimWPWrapper
                        = new TimWPWrapperJasper(
                                tim.getPenanggungJawab().getNipPegawai(),
                                tim.getPenanggungJawab().getNamaPegawai(),
                                tim.getPenanggungJawab().getPangkat(),
                                tim.getPenanggungJawab().getGolongan(),
                                tim.getPenanggungJawab().getJabatanTim(),

                                tim.getSupervisor().getNipPegawai(),
                                tim.getSupervisor().getNamaPegawai(),
                                tim.getSupervisor().getPangkat(),
                                tim.getSupervisor().getGolongan(),
                                tim.getSupervisor().getJabatanTim(),

                                tim.getTim().getNamaTim(),
                                wajibPajakList
                        );
                JRBeanCollectionDataSource beanColDataSourceWp =
                        new JRBeanCollectionDataSource(wajibPajakList);
                objTimWPWrapper.setWajibPajakJasper(beanColDataSourceWp);
                objTimWPWrapper.setListWP(tim.getWajibPajaks());

                dummi.getTimWPWrapperJaspers().add(objTimWPWrapper);

            }

            //data dummi\
            
            int index = 0;
            for(TimWPWrapperJasper timWP : dummi.getTimWPWrapperJaspers()){
                for(WajibPajak wp : timWP.getListWP()){
                    System.out.println("Masuk wp : " + wp.getNamaWajibPajak());
                    System.out.println("Index wp : " + index);

                    reportService.createPersiapanPeminjamanBuku(persiapanWrapper, wp, index);
                    index ++;
                    switch(wp.getJenisWp()){
                        case 0: 
                            reportService.createPersiapanDokumenPinjaman(WP.RESTORAN, wp, dummi);
                            reportService.createQuesionerRestoran();
                            break;
                        case 1:
                            reportService.createPersiapanDokumenPinjaman(WP.HOTEL, wp, dummi);
                            reportService.createQuesionerRestoran();
                            break;
                    }
                }
            }
            System.out.println("Beres Surat Peminjaman Buku");
            
        
    }

    private void addFromFXML() {
        idWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "Id WP");
        namaWP
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "Nama WP");
        nomorSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "Nomor surat");
        tanggalSurat 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "Tanggal surat");
        action 
                = TableHelper.getTableColumnByName(AturNomorTanggalSuratPeminjamanTable, "Action");
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
            
            dataCollection.add(new PersiapanNomorTanggalSuratPeminjamanTableWrapper(
                    obj.getWajibPajak().getNpwpd(),
                    obj.getWajibPajak().getNamaWajibPajak(),
                    obj.getNomorPeminjamanDokumen(),
                    (obj.getTanggalPeminjamanDokumen()!= null)
                            ?formatter.format(obj.getTanggalPeminjamanDokumen())
                            :"",
                    aturBtn
            ));
            
        }
        for (final PersiapanNomorTanggalSuratPeminjamanTableWrapper obj
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
                    Pane formInputNomorTglSuratPeminjaman = null;
                    try {
                        formInputNomorTglSuratPeminjaman = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormInputSuratPeminjamanNomorTanggal.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Form input nomor dan tanggal surat peminjaman");
                    stage.setScene(new Scene(formInputNomorTglSuratPeminjaman));
                    stage.show();
                }
                
            });
        }
    }

    private void associateDataWithColumn() {
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("namaWP"));
        nomorSurat.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("nomorSurat"));
        tanggalSurat.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("tanggalSurat"));
        action.setCellValueFactory(new PropertyValueFactory<PersiapanNomorTanggalSuratPeminjamanTableWrapper, String>("Action"));
    }
    
}
