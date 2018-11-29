package com.bekasidev.app;

import com.bekasidev.app.model.*;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.viewfx.javafxapplication.JavaFXApplication;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    
    public static void main( String[] args )
    {
        /**
         * init main frame
         */
        launch(args);
        
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setVisible(true);
//        WajibPajak wp = ServiceFactory.getWajibPajakService().getWajibPajakById("1542006560917");
//        List<Pegawai> listPegawai = new ArrayList<>();
//        listPegawai.add(new Pegawai("01", "123", "SASUKE", "IV/a", "Pelaksana Lapangan"));
//        listPegawai.add(new Pegawai("01", "321", "SAKURA", "IV/a", "Pelaksana Lapangan"));
//
//        BerkasPersiapan bp = new BerkasPersiapan();
//        bp.setNpwpd("1542006560917");
//        bp.setKotaTerbit("Bekasi");
//        bp.setAlamatJalan(wp.getJalan());
//        bp.setAlamatKecamatan(wp.getKecamatan());
//        bp.setAlamatDi(wp.getDesa());
//        bp.setNpwpd(wp.getNamaWajibPajak());
//        bp.setMasaPajakAwal("7 Juni 2017");
//        bp.setMasaPajakAkhir("12 Juni 2018");
//        bp.setNomorSurat("123/321/123/2018");
//        bp.setSifat("Rahasia");
//        bp.setLampiran("1 (satu) lampiran");
//        bp.setPerihal("Pemeriksaan pajak tahunan");
//        bp.setNomorSp("321/123/SP/2018");
//        bp.setTanggalSp("12 Juni 2018");
//        ServiceFactory.getBerkasPersiapanService().getDokumenPinjaman(bp,WP.RESTORAN);
//        bp.setNamaTim("Tim 7");
//        bp.setListPegawai(listPegawai);
//        bp.setJabatanPenandatangan("Kepala Badan Pendapatan Daerah");
//        bp.setNamaPenandatangan("Fulan");
//        ServiceFactory.getBerkasPersiapanService().createBerkasPersiapan(bp);

//        wp.setNamaWajibPajak("bebek kaleyo");
//        wp.setJenisWp((short) 0);
//        wp.setJalan("Jalan deltamas");
//        wp.setKecamatan("Cikarang pusat");
//        wp.setDesa("Sukamahi");
//        ServiceFactory.getWajibPajakService().createDataWP(wp);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setTitle("Aplikasi Perpajakan");

            ServiceFactory.getRekapitulasiService().setBulanRekapitulasi(
                    new RekapitulasiWrapper(),
                    new Date((long) 1534788900526.0),
                            new Date((long) 1574273700526.0)
            );
            System.out.println(getClass().getClassLoader().getResource("javafxresources/RootPane.fxml").getPath());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("javafxresources/RootPane.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("javafxresources/rootpane.css").toString());
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//        Menu menu = new Menu("123345", "123123", "Ayam Bakar", (short) 0, 17000);
//        Benchmark benchmark = new Benchmark("123123", "321321", "123123", "1 Kg Daging",
//                8, (float) 1.5, "Kg", "", "Daging");

//        MenuService menuService = new MenuServiceImpl();
//        menuService.createMenu(menu);

//        BenchmarkService benchmarkService = new BenchmarkServiceImpl();
//        benchmarkService.createBenchmark(benchmark);
//        Pembukuan pembukuan = new Pembukuan("123123", "321321", "123123", "Belanja Ayam Bulan Ini", 2500, "Kg", 0,"");
//        PembukuanService pembukuanService = new PembukuanServiceImpl();
//        pembukuanService.createPembukuan(pembukuan);
//        List<Potensi> potensis = new ArrayList<>();
//        Potensi potensi = new Potensi("123123", "321321", "123345", 17000,
//                1600, 0, "");
//        potensis.add(potensi);
//        potensi = new Potensi("123123", "321321", "123345", 7000,
//                600, 0, "");
//        potensis.add(potensi);

//        PotensiService potensiService = new PotensiServiceImpl();
//        SptpdWrapper sptpdWrapper = new SptpdWrapper();
//        sptpdWrapper = potensiService.getAllPotensi("123123", "321321");
//        for(PotensiJoinWrapper potensiJoinWrapper : sptpdWrapper.getListMakanan()){
//            System.out.println(potensiJoinWrapper.getNamaMenu() + "\t" + potensiJoinWrapper.getFrekuensiPenjualan() + "\t"
//            + potensiJoinWrapper.getFrekuensiPotensi() + "\t" + potensiJoinWrapper.getHargaSatuan() + "\t"
//            + potensiJoinWrapper.getJumlahPenjualan() + "\t" + potensiJoinWrapper.getJumlahPotensi());
//        }
//        potensiService.calculatePenjualan(potensis);
//        potensiService.createPotensi(potensis);
