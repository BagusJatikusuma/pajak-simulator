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
 * @author Bayu Arafli
 */
public class SubContentMain extends JPanel{
    private MainFrame mainFrame;
    
    public SubContentMain() {
        super(new BorderLayout());
    }
    
    public SubContentMain(MainFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame;
    }
    
    public void initSubContentMain(){
        SideContentPanel sideContentPanel = new SideContentPanel();
        sideContentPanel.initSideContentPanel();
        
        ContentPanel contentPanel = new ContentPanel(mainFrame);
        contentPanel.initPanel();
        
        if(mainFrame == null) {
            System.out.println("Main frame sub content main is null");
        } else {
            System.out.println("main frame sub content main is not null");
        }
        
        HeaderFramePanel headerContentPanel = new HeaderFramePanel(mainFrame);
        headerContentPanel.init(Color.WHITE);
        
//        this.add(sideContentPanel, BorderLayout.LINE_START);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(headerContentPanel, BorderLayout.PAGE_START);

    }
        
}
