package com.example;
import java.util.*;

public class Board {
    public synchronized void submitWord(String word, Player player, int points) {
        System.out.println(player.getNamePlayer() + " submitted word: " + word + " for " + points + " points.");
    }
}
