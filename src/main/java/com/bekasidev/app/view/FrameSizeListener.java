/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author bagus
 */
public class FrameSizeListener extends ComponentAdapter {
    
    private MainFrame mainFrame;

    public FrameSizeListener() {
    }

    public FrameSizeListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        Dimension screenSize = mainFrame.getToolkit().getScreenSize();
        
        mainFrame.setSize((int) (screenSize.getWidth()-50), (int) (screenSize.getHeight()-75));
    }
    
}
