package com.example;

import java.awt.*;
import java.io.Serializable;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Player implements Serializable{
    private final String Name;
    private int score = 0;
    private Color color;

    public Player(String name, Color color) {
        this.Name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return Name;
    }

    public int getScore() {
        return score;
    }

    public void addLine(Line line) {
            score += (int)sqrt(pow(line.end().x()-line.start().x(), 2)-pow(line.end().y()-line.start().y(), 2));
    }


}
