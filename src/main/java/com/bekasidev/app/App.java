package com.bekasidev.app;

import com.bekasidev.app.dao.PembukuanDao;
import com.bekasidev.app.dao.impl.PembukuanDaoImpl;
import com.bekasidev.app.model.Benchmark;
import com.bekasidev.app.model.Menu;
import com.bekasidev.app.model.Pembukuan;
import com.bekasidev.app.service.backend.BenchmarkService;
import com.bekasidev.app.service.backend.MenuService;
import com.bekasidev.app.service.backend.PembukuanService;
import com.bekasidev.app.service.backend.impl.BenchmarkServiceImpl;
import com.bekasidev.app.service.backend.impl.MenuServiceImpl;
import com.bekasidev.app.service.backend.impl.PembukuanServiceImpl;
import com.bekasidev.app.view.FrameAttributeConstant;
import com.bekasidev.app.view.MainFrame;

import java.awt.*;

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
//        Menu menu = new Menu("123345", "123123", "Ayam Bakar", (short) 0, 17000);
//        Benchmark benchmark = new Benchmark("123123", "321321", "123123", "1 Kg Daging",
//                8, (float) 1.5, "Kg", "", "Daging");

//        MenuService menuService = new MenuServiceImpl();
//        menuService.createMenu(menu);

//        BenchmarkService benchmarkService = new BenchmarkServiceImpl();
//        benchmarkService.createBenchmark(benchmark);
        Pembukuan pembukuan = new Pembukuan("123123", "321321", "123123", "Belanja Ayam Bulan Ini", 2500, "Kg", 0,"");
        PembukuanService pembukuanService = new PembukuanServiceImpl();
        pembukuanService.createPembukuan(pembukuan);
        MainFrame mainFrame = new MainFrame();
        mainFrame.init(FrameAttributeConstant.MAIN_FRAME_WIDTH, 
                        FrameAttributeConstant.MAIN_FRAME_HEIGHT, 
                        FrameAttributeConstant.MAIN_FRAME_X, 
                        FrameAttributeConstant.MAIN_FRAME_Y);
        mainFrame.setVisible(true);
        
    }
}
