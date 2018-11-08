package com.bekasidev.app.view.timviewcomponent;

import com.bekasidev.app.view.util.ComponentCollectorProvider;

import javax.swing.*;
import java.awt.*;

public class TimContentPanel extends JPanel {
    public TimContentPanel() {
        ComponentCollectorProvider.addComponent("tim_content_panel", this, null, null);
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout(10,0));
        this.setBackground(Color.BLUE);

        TimTableComponentPanel timTableComponentPanel = new TimTableComponentPanel();
        this.add(timTableComponentPanel, BorderLayout.LINE_START);

        TimResponseContainer timResponseContainer = new TimResponseContainer();
        this.add(timResponseContainer, BorderLayout.LINE_END);
    }
}

