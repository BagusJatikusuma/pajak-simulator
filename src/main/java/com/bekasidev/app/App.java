package com.bekasidev.app;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.Singleton;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
import com.bekasidev.app.view.FrameAttributeConstant;
import com.bekasidev.app.view.MainFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

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
        RestoranTransaction rt = new RestoranTransaction("123123",
                "321321",
                3000000,
                1500000,
                900000,
                110,
                150,
                100,
                0,0,0,0,0);
//        Restoran restoran = new Restoran();
//        restoran.setNamaRestoran("SOLARIA");
        RestoranTransactionService restoranTransactionService = Singleton.getRestoranTransactionService();
        restoranTransactionService.calculatePotensiPajakRestoran(rt);
//        RestoranService restoranService = new RestoranServiceImpl();
//        restoranService.createDataRestoran(restoran);
        System.out.println("Setahun " + round(rt.getPajakSetahun()));
        System.out.println("Perbulan " + round(rt.getPajakPerBulan()));
//        restoranTransactionService.createRestoranTransaction(rt);
        MainFrame mainFrame = new MainFrame();
        mainFrame.init(FrameAttributeConstant.MAIN_FRAME_WIDTH, 
                        FrameAttributeConstant.MAIN_FRAME_HEIGHT, 
                        FrameAttributeConstant.MAIN_FRAME_X, 
                        FrameAttributeConstant.MAIN_FRAME_Y);
        mainFrame.setVisible(true);
        
    }
}
