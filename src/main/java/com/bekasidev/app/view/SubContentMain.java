/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class SubContentMain extends JPanel{

    public SubContentMain() {
        super(new BorderLayout());
    }
    
    public void initSubContentMain(){
        SideContentPanel sideContentPanel = new SideContentPanel();
        sideContentPanel.initSideContentPanel();
        
        ContentPanel contentPanel = new ContentPanel();
        contentPanel.initPanel();
        
//        this.add(sideContentPanel, BorderLayout.LINE_START);
        this.add(contentPanel, BorderLayout.CENTER);

    }
    
}
