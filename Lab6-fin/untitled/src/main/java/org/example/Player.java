package org.example;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Player implements Serializable{
    private final String Name;
    private int score = 0;
    private final Color color;
    List<Point> points;

    public Player(String name, Color color) {
        this.Name = name;
        this.color = color;
        points = new ArrayList<>();
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
            System.out.println("For Line: " + line);
            System.out.println("Line value= "+(int)sqrt(pow(line.end().x()-line.start().x(), 2)+pow(line.end().y()-line.start().y(), 2)));
            score += (int)sqrt(pow(line.end().x()-line.start().x(), 2)+pow(line.end().y()-line.start().y(), 2));
            System.out.println(Name+" Score: "+score);
    }
    public void addPoint(Point point) {
        points.add(point);
    }
    public List<Point> getPoints() {
        return points;
    }


}
