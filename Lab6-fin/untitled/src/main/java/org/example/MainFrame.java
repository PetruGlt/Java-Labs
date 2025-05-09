package org.example;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    public MainFrame() {
        super("My Drawing Application");
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
    }
}