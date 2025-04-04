package com.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton restartGameBtn;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Number of dots");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        restartGameBtn = new JButton("Start a New Game");
        add(label);
        add(spinner);
        add(restartGameBtn);

//        restartGameBtn.addActionListener(this::restart);
    }

}