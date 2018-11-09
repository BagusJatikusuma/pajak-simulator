/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhiburanview;

import com.bekasidev.app.view.pajakparkirview.*;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakHiburanTablePanel extends JPanel{

    public PajakHiburanTablePanel() {
        ComponentCollectorProvider.addComponent("pajak_hiburan_table_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setBackground(Color.CYAN);
    }
    
}
