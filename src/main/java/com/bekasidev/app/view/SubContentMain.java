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
import javax.swing.JScrollPane;

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
//        SideContentPanel sideContentPanel = new SideContentPanel();
//        sideContentPanel.initSideContentPanel();

        JScrollPane scroll = new JScrollPane();
    
        ContentPanel contentPanel = new ContentPanel(mainFrame);
        contentPanel.initPanel();
        contentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 1300));
        
        scroll.setPreferredSize(new Dimension(mainFrame.getWidth()/2, mainFrame.getHeight()-100));
        scroll.setViewportView(contentPanel);
        
        //add header panel
//        HeaderFramePanel headerContentPanel = new HeaderFramePanel(mainFrame);
//        headerContentPanel.init(Color.WHITE);
        
        this.add(scroll, BorderLayout.CENTER);

    }
        
}
