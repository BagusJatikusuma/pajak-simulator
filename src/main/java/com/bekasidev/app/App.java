package com.bekasidev.app;

import com.bekasidev.app.dao.PembukuanDao;
import com.bekasidev.app.dao.impl.PembukuanDaoImpl;
import com.bekasidev.app.model.Benchmark;
import com.bekasidev.app.model.Menu;
import com.bekasidev.app.model.Pembukuan;
import com.bekasidev.app.model.Potensi;
import com.bekasidev.app.service.backend.BenchmarkService;
import com.bekasidev.app.service.backend.MenuService;
import com.bekasidev.app.service.backend.PembukuanService;
import com.bekasidev.app.service.backend.PotensiService;
import com.bekasidev.app.service.backend.impl.BenchmarkServiceImpl;
import com.bekasidev.app.service.backend.impl.MenuServiceImpl;
import com.bekasidev.app.service.backend.impl.PembukuanServiceImpl;
import com.bekasidev.app.service.backend.impl.PotensiServiceImpl;
import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.wrapper.PotensiJoinWrapper;
import com.bekasidev.app.wrapper.SptpdWrapper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        /**
         * init main frame
         */
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        
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
