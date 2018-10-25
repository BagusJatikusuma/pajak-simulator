/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class LandingMainPanel extends JPanel {
    private MainFrame mainFrame;
    
    public LandingMainPanel() {
        super();
    }
    
    public LandingMainPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        
        HeaderFramePanel headerFramePanel = new HeaderFramePanel(mainFrame);
        headerFramePanel.init(this.getBackground());
        
        LandingPagePanel landingPagePanel = new LandingPagePanel(mainFrame);
        landingPagePanel.init();
        
        this.add(headerFramePanel, BorderLayout.PAGE_START);
        this.add(landingPagePanel, BorderLayout.CENTER);
    }
    
}
