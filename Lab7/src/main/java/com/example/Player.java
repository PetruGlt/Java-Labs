package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()) {
            return false;
        }

        String word = extracted.stream()
                .map(Tile::getCharacter)
                .map(String::valueOf)
                .collect(Collectors.joining());

        if (game.getDictionary().isWord(word)) {
            game.getBoard().addWord(this, word);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return true;
    }

    @Override
    public void run() {
        while (running) {
            boolean success = submitWord();
            if (!success) {
                running = false;
            }
        }
    }
}
