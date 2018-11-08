package com.bekasidev.app.view.timviewcomponent;

import com.bekasidev.app.view.util.ComponentCollectorProvider;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TimResponseContainer extends JPanel {
    public TimResponseContainer() {
        ComponentCollectorProvider.addComponent("tim_response_panel", this,null,null);
        init();
    }

    public void init() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(500,500));

        AddTimPanel addTimPanel = new AddTimPanel();
        addTimPanel.setSize(500,500);
        addTimPanel.setLocation(0,0);

        this.add(addTimPanel);
    }
}
