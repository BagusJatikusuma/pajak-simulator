package com.bekasidev.app.view;

import com.bekasidev.app.viewold.LandingPagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuBarPanelRestoran extends JPanel {

    public MenuBarPanelRestoran(){
        super();
    }

    public void init(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
//        this.setPreferredSize(new Dimension(200,100));
        this.setBackground(Color.decode("#f3f3f3"));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#c7c7c7")));

        // menu manajemen
        Image imgMenuManajemen = null;
        try {
            imgMenuManajemen
                    = ImageIO.read(getClass().getClassLoader().getResource("images/logo_kab_bekasi.png"));
        } catch (IOException ex) {
            Logger.getLogger(LandingPagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image imgMenuMenejemenResult = getScaledImage(imgMenuManajemen, 41, 41);
        JButton tombolMenuManajemen = new JButton("Menejemen", new ImageIcon(imgMenuMenejemenResult));
        tombolMenuManajemen.setVerticalTextPosition(AbstractButton.BOTTOM);
        tombolMenuManajemen.setHorizontalTextPosition(AbstractButton.CENTER);
        tombolMenuManajemen.setPreferredSize(new Dimension(130,90));

        // menu pemeriksaan
        Image imgMenuPemeriksaan = null;
        try {
            imgMenuPemeriksaan
                    = ImageIO.read(getClass().getClassLoader().getResource("images/logo_kab_bekasi.png"));
        } catch (IOException ex) {
            Logger.getLogger(LandingPagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image imgMenuPemeriksaanResult = getScaledImage(imgMenuPemeriksaan, 41, 41);
        JButton tombolMenuPemeriksaan = new JButton("Pemeriksaan", new ImageIcon(imgMenuPemeriksaanResult));
        tombolMenuPemeriksaan.setVerticalTextPosition(AbstractButton.BOTTOM);
        tombolMenuPemeriksaan.setHorizontalTextPosition(AbstractButton.CENTER);
        tombolMenuPemeriksaan.setPreferredSize(new Dimension(130,90));

        this.add(tombolMenuManajemen);
        this.add(tombolMenuPemeriksaan);

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
