/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *
 * @author bagus
 */
public class FrameSizeListener implements WindowStateListener {
    
    private MainFrame mainFrame;

    public FrameSizeListener() {
    }

    public FrameSizeListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        Dimension screenSize = mainFrame.getBounds().getSize();
        
        mainFrame.setSize((int) (screenSize.getWidth()), (int) (screenSize.getHeight()));
        System.out.println("new size is "+mainFrame.getWidth()+"; "+mainFrame.getHeight());
        
        JPanel panel = (JPanel) mainFrame.getContentPane().getComponent(1);
        
        if (panel instanceof LandingMainPanel) {
            //nothin currently
        }
        else if (panel instanceof SubContentMain) {
            System.err.println("is subconten main");
//            
//            JScrollPane scroll = (JScrollPane)panel.getComponent(0);
//            scroll.setPreferredSize(new Dimension(mainFrame.getWidth()/2, mainFrame.getHeight()-100));
//            
//            JViewport viewPort = (JViewport) scroll.getComponent(0);
//            ContentPanel contentPanel = (ContentPanel) viewPort.getView();
//            
//            contentPanel.resetComponentSize();
        }
        
    }
    
}
