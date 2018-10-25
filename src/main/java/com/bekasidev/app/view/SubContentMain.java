/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class SubContentMain extends JPanel{
    private MainFrame mainFrame;
    
    public SubContentMain() {
        super();
    }
    
    public SubContentMain(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void initSubContentMain(){
        this.setLayout(new BorderLayout());
        SideContentPanel sideContentPanel = new SideContentPanel();
        sideContentPanel.initSideContentPanel();
        ContentPanel contentPanel = new ContentPanel(mainFrame);
        contentPanel.initPanel();
        //add header panel
        HeaderFramePanel headerContentPanel = new HeaderFramePanel(mainFrame);
        headerContentPanel.init(Color.WHITE);
        
        this.add(contentPanel, BorderLayout.CENTER);

    }
        
}
