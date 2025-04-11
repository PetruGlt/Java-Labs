package com.example;

public class Tile {
    private final char character;
    private final int points;

    public Tile(char character, int points) {
        this.character = character;
        this.points = points;
    }

    public char getCharacter() {
        return character;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Character: " + character + " Points: " + points + "\n";
    }
}
