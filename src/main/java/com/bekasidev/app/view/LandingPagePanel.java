/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class LandingPagePanel extends JPanel {

    private MainFrame mainFrame;
    
    public LandingPagePanel() {
        super();
    }
    public LandingPagePanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void init() {
        this.setLayout(null);
        
        addLogoKabBekasi();
    }
    
    public void addLogoKabBekasi() {
        JLabel iconLbl = new JLabel();
        JLabel iconExit = new JLabel();
        
        Image img = null;
        try {
            img
                = ImageIO.read(getClass().getClassLoader().getResource("images/logo_kab_bekasi.png"));
        } catch (IOException ex) {
            Logger.getLogger(LandingPagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image imgResult = getScaledImage(img, 300, 333);
        
        iconLbl.setIcon(new ImageIcon(imgResult));
        iconLbl.setSize(300, 333);
        iconLbl.setLocation(
                ((mainFrame.getWidth()/2 - iconLbl.getWidth()/2)), 
                (mainFrame.getHeight()/2 - iconLbl.getHeight()/2)-100);
        
        this.add(iconLbl);
        
        
        
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
}
