package com.bekasidev.app.view;

import com.bekasidev.app.view.timviewcomponent.TimContentPanel;
import com.bekasidev.app.view.util.ComponentCollectorProvider;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    public ContentPanel() {
        ComponentCollectorProvider.addComponent("content_panel",this,null, null);
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());

        TimContentPanel timContentPanel = new TimContentPanel();
    }
}
