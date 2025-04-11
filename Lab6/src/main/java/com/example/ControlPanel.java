package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton load = new JButton("Load");
    JButton save = new JButton("Save");
    JButton reset = new JButton("Reset");
    JButton export = new JButton("Export to PNG");


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 5));
        add(exitBtn);
        add(load);
        add(save);
        add(reset);
        add(export);


        exitBtn.addActionListener(this::exitGame);
        load.addActionListener(this::loadGame);
        save.addActionListener(this::saveGame);
        reset.addActionListener(this::clearCanvas);
        export.addActionListener(this::exportToImage);


    }
        private void exitGame(ActionEvent e) {
            frame.dispose();
        }

    private void loadGame(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                DrawingPanel loadedCanvas = (DrawingPanel) ois.readObject();

                this.frame.canvas.points = loadedCanvas.points;
                this.frame.canvas.lines = loadedCanvas.lines;
                this.frame.canvas.players = loadedCanvas.players;

                frame.canvas.createOffscreenImage();
                frame.canvas.repaint();

                for(Point p : frame.canvas.points) {
                    frame.canvas.paintSingleDot(p);
                }

                int turn = frame.canvas.lines.size();
                for(Line line : frame.canvas.lines) {
                    frame.canvas.paintSingleDot(line.end());
                    frame.canvas.paintSingleDot(line.start());
                    turn -= 1;
                    frame.canvas.paintLine(line.end(),line.start(),  frame.canvas.players[turn%2].getColor());
                }

                System.out.println("Game loaded successfully!");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to load the game!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveGame(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()))) {
                oos.writeObject(frame.canvas);
                System.out.println("Game saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save the game!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearCanvas(ActionEvent e) {
        frame.canvas.clean();
        System.out.println("Cleared canvas");
    }
    private void exportToImage(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
             frame.canvas.exportToPNG(fileChooser.getSelectedFile().getPath());
        }
    }
}