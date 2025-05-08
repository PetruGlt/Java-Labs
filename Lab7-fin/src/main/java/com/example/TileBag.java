package com.example;

import java.util.*;

public class TileBag {
    private final List<Tile> tiles = new ArrayList<>();
    private final Random random = new Random();

    public TileBag() {
        for (char c = 'A'; c <= 'Z'; c++) {
            for (int i = 0; i < 10; i++) {
                tiles.add(new Tile(c, random.nextInt(10) + 1));
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int count) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < count && !tiles.isEmpty(); i++) {
            extracted.add(tiles.remove(0));
        }
        return extracted;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}

