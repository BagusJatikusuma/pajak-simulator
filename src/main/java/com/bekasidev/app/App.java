package com.bekasidev.app;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import com.bekasidev.app.view.FrameAttributeConstant;
import com.bekasidev.app.view.MainFrame;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
        RestoranService restoranService = new RestoranServiceImpl();
        List<Restoran> restorans = new ArrayList<>();
        restorans = restoranService.getAllRestoran();
        for(Restoran restoran : restorans)
            System.out.println(restoran.getIdRestoran() + "\t" + restoran.getNamaRestoran());
        MainFrame mainFrame = new MainFrame();
        mainFrame.init(FrameAttributeConstant.MAIN_FRAME_WIDTH, 
                        FrameAttributeConstant.MAIN_FRAME_HEIGHT, 
                        FrameAttributeConstant.MAIN_FRAME_X, 
                        FrameAttributeConstant.MAIN_FRAME_Y);
        mainFrame.setVisible(true);
        
    }
}
