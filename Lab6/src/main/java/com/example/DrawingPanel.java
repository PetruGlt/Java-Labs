package com.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel implements Serializable{
    final transient MainFrame frame;
    int canvasWidth = 400, canvasHeight = 400;
    transient BufferedImage image;
    transient Graphics2D offscreen;
    List<Point> points= new ArrayList<>();
    List<Line> lines=new ArrayList<>();
    private Point selectedPoint1=null;
    Player[] players = {new Player("Player1" ,Color.RED) , new Player("Player2" ,Color.BLUE)};

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        createOffscreenImage();
        initMouseHandler();


    }

    public void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        offscreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void paintDots(int nrOfPoints) {
        offscreen.setColor(Color.BLACK);
        for (int i = 0; i < nrOfPoints; i++) {
            Random rand= new Random();
            int x= rand.nextInt(370) + 30;
            int y= rand.nextInt(370) + 30;
            offscreen.fillOval(x , y, 10, 10);
            points.add(new Point(x, y));
        }
    }

    public void paintSingleDot(Point point) {
        offscreen.setColor(Color.BLACK);
        offscreen.fillOval(point.x(), point.y(), 10, 10);
    }

    public void paintLine(Point p1, Point p2, Color playerColor) {
        offscreen.setColor(playerColor);
//        lines.add(new Line(p1,p2));
        offscreen.drawLine(p1.x()+5, p1.y()+5, p2.x()+5, p2.y()+5);

    }

    public void printPoints(){
        System.out.println(points);
    }

    public void startGame(JSpinner spinner) {
        points.clear();
        lines.clear();
        createOffscreenImage();
        int nrOfPoints = (int) spinner.getValue();
        paintDots(nrOfPoints);
        repaint();

//        paintLine(points.get(0), points.get(1));
    }

    private void startGame2(JSpinner spinner) {
        int nrOfPoints = (int) spinner.getValue();
        paintDots(nrOfPoints);
//        paintLine(points.get(0), points.get(1));
    }



    public void clean(){
        players[0]=new Player(players[0].getName(),Color.RED);
        players[1]=new Player(players[1].getName(),Color.BLUE);
        selectedPoint1=null;
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        repaint();
    }

    private Point aproximatePoint(int x, int y) {
        for (Point p : points) {
            if ((p.x()-x <= 10 && p.x()-x>=-10) &&(p.y()-y <= 10 && p.y()-y>=-10)) {
                return p;
            }
        }
        return null;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        createOffscreenImage();
    }


    private void initMouseHandler() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point selectedPoint = aproximatePoint(e.getX(), e.getY());
                if (selectedPoint != null) {
                if (points.contains(selectedPoint)) {
                    if (selectedPoint1 == null) {
                        selectedPoint1 = selectedPoint;
                        points.remove(selectedPoint);
                    } else {
                        paintLine(selectedPoint1, selectedPoint, players[(lines.size()%2)].getColor() );
                        repaint();
                        Line newLine = new Line(selectedPoint1, selectedPoint);
                        players[(lines.size()%2)].addLine(newLine);
                        lines.add(newLine);
//                        points.remove(selectedPoint1);
                        points.remove(selectedPoint);
                        selectedPoint1 = null;
                        gameOver();
                    }
                }
            }
            }
        });
    }

    private void gameOver(){
        if(points.size()==0||points.size()==1){
            System.out.println("Game Over");
            System.out.println(players[0].getName() +" : "+players[0].getScore() +"\n---------------------\n"+ players[1].getName() + " : "+ players[1].getScore());
            players[0]=new Player(players[0].getName(),Color.RED);
            players[1]=new Player(players[1].getName(),Color.BLUE);
        }
    }

    public void exportToPNG(String fileName) {
        try {
            ImageIO.write(image, "png", new File(fileName));
            System.out.println("Image exported to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}