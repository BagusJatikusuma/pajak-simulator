package com.bekasidev.app;

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
        MainFrame mainFrame = new MainFrame();
        mainFrame.init(FrameAttributeConstant.MAIN_FRAME_WIDTH, 
                        FrameAttributeConstant.MAIN_FRAME_HEIGHT, 
                        FrameAttributeConstant.MAIN_FRAME_X, 
                        FrameAttributeConstant.MAIN_FRAME_Y);
        mainFrame.setVisible(true);
        
    }
}
