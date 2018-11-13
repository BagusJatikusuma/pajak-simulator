/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.viewold.LandingPagePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class CustomHeaderPanel extends JPanel {

    public CustomHeaderPanel() {
        init();
    }
    
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBackground(Color.decode("#144429"));
        this.setPreferredSize(new Dimension(0,35));
        
        // close
        Image imgClose = null;
        try {
            imgClose
                    = ImageIO.read(getClass().getClassLoader().getResource("images/close-windows-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(LandingPagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image imgCloseResult = getScaledImage(imgClose, 51, 21);
        JLabel close = new JLabel();
        close.setIcon(new ImageIcon(imgCloseResult));
        close.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mainFrame
                    = (MainFrame) ComponentCollectorProvider
                    .getComponentMapper()
                    .get("main_frame")
                    .getComponent();
                mainFrame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(close);
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
