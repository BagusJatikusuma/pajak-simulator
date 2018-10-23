package com.bekasidev.app;

import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
import com.bekasidev.app.view.FrameAttributeConstant;
import com.bekasidev.app.view.MainFrame;

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
                360,645000000);
        RestoranTransactionService restoranTransactionService = new RestoranTransactionServiceImpl();
        System.out.println((int)restoranTransactionService.calculatePotensiPajakRestoran(rt));
        MainFrame mainFrame = new MainFrame();
        mainFrame.init(FrameAttributeConstant.MAIN_FRAME_WIDTH, 
                        FrameAttributeConstant.MAIN_FRAME_HEIGHT, 
                        FrameAttributeConstant.MAIN_FRAME_X, 
                        FrameAttributeConstant.MAIN_FRAME_Y);
        mainFrame.setVisible(true);
        
    }
}
