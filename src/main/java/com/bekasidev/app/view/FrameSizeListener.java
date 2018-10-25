/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JPanel;

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
            ContentPanel contentPanel = (ContentPanel)panel.getComponent(0);
            contentPanel.resetComponentSize();
        }
        
    }
    
}