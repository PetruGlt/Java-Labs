package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;

    int canvasWidth = 600, canvasHeight = 400;
    private int dotSize = 20;

    BufferedImage image; //the offscreen image
    Graphics2D offscreen; //the offscreen graphics

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintDots(g);
//        paintLines(g);
    }

    private void paintDots(Graphics2D g) {
        g.setColor(Color.RED);

        Random random = new Random();
        for (int x = dotSize; x < canvasWidth; x += random.nextInt(50)+random.nextInt(70) + dotSize) {
            for (int y = dotSize; y < canvasHeight; y += random.nextInt(50)+random.nextInt(100) + dotSize) {
                g.fillOval(x - dotSize/2, y - dotSize/2, dotSize, dotSize);
            }
        }
    }
}