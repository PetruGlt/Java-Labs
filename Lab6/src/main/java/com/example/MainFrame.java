package com.example;
import javax.swing.*;
import java.awt.*;
//
//public class MainFrame extends JFrame {
//    ConfigPanel configPanel;
//    ControlPanel controlPanel;
//    DrawingPanel canvas;
//    public MainFrame() {
//        super("My Drawing Application");
//        init();
//    }
//    private void init() {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        canvas = new DrawingPanel(this);
//        controlPanel = new ControlPanel(this);
//        configPanel = new ConfigPanel(this);
//        setLayout(new BorderLayout());
//        add(canvas, BorderLayout.CENTER); //this is BorderLayout.CENTER
//        add(configPanel, BorderLayout.NORTH);
//        add(controlPanel, BorderLayout.SOUTH);
//        pack();
//    }
//}

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
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        configPanel = new ConfigPanel(this);
//arrange the components in the container (frame)

        setLayout(new BorderLayout());
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
//invoke the layout manager
        pack();
    }
}