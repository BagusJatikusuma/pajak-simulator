/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
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
public class HeaderFramePanel extends JPanel {
    MainFrame mainFrame;

    public HeaderFramePanel() {
        super();
    }
    public HeaderFramePanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void init(Color colorHeader) {
        this.setLayout(new BorderLayout());
        this.setBackground(colorHeader);
        
        Image imgExit = null;
        try {
            imgExit
                = ImageIO.read(getClass().getClassLoader().getResource("images/close-windows-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(LandingPagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JLabel iconExit = new JLabel();
        
        Image imgExitResult = getScaledImage(imgExit, 61, 21);
        
        iconExit.setIcon(new ImageIcon(imgExitResult));
        iconExit.setSize(61, 21);
        
//        if(mainFrame == null) {
//            System.out.println("Main frame is null");
//        } else {
//            System.out.println("main frame is not null");
//        }
        
        iconExit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitPressed(evt, mainFrame);
            }
        });
        
        this.add(iconExit, BorderLayout.AFTER_LINE_ENDS);
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    private void exitPressed(MouseEvent evt, MainFrame mainFrameRoot) {
        mainFrameRoot.dispose();
    }
    
}
