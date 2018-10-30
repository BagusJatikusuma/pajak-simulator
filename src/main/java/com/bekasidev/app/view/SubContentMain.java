/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Bayu Arafli
 */
public class SubContentMain extends JPanel{
    private MainFrame mainFrame;
    private JLabel  labelHeaderFormIdentitas;
    private JPanel  panelHeaderFormIdentitas;
    
    public SubContentMain() {
        super();
    }
    
    public SubContentMain(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void resetComponentSize(){
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
    }
    
    public void initSubContentMain(){
        this.setLayout(new BorderLayout());
        
        SideContentPanel sideContentPanel = new SideContentPanel(mainFrame);
        sideContentPanel.initSideContentPanel();

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
        this.add(sideContentPanel, BorderLayout.LINE_START);
        header();

    }
    
    public void header(){
        //===== header form identitas =====//
        panelHeaderFormIdentitas = new JPanel(new GridBagLayout());
        panelHeaderFormIdentitas.setBackground(Color.decode("#4377ca"));
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
        panelHeaderFormIdentitas.setLocation(0, 0);
        panelHeaderFormIdentitas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        labelHeaderFormIdentitas = new JLabel("RESTORAN");
        labelHeaderFormIdentitas.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelHeaderFormIdentitas.setForeground(Color.WHITE);
        labelHeaderFormIdentitas.setSize(200, 50);
        
        // add to panelHeader
        panelHeaderFormIdentitas.add(labelHeaderFormIdentitas);
        
        // add to panel
        this.add(panelHeaderFormIdentitas, BorderLayout.PAGE_START);
        //===== header form identitas =====//
    }
        
}
