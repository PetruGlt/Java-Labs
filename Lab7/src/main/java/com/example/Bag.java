package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag {
    List<Tile> tiles = new ArrayList<>();
    Random rand = new Random();

    public Bag() {
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i <= 9; i++) {
                tiles.add(new Tile(c, rand.nextInt(10) + 1));
            }
        }
        Collections.shuffle(tiles);
//        System.out.println(tiles);
    }

    public synchronized List<Tile> extractTiles(int amount) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if(tiles.isEmpty()) {
                break;
            }
            extracted.add(tiles.remove(0));
        }
        return extracted;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
