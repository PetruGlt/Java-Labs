package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton newGame;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        label = new JLabel("Number of dots");
        spinner = new JSpinner(new SpinnerNumberModel(3, 2, 100, 1));
        newGame = new JButton("New Game");


        newGame.addActionListener(this::newGameAction);
        setLayout(new FlowLayout());


        add(label);
        add(spinner);


        add(newGame);
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    private void newGameAction(ActionEvent e) {
        frame.canvas.clean();
        frame.canvas.startGame(spinner);
        int numDots = (int) spinner.getValue();
        System.out.println("Starting new game with " + numDots + " dots.");
    }

}