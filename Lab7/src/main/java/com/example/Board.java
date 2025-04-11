package com.example;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final List<String> words = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public synchronized void addWord(Player player, String word) {
        lock.lock();
        try{
            words.add(word);
            System.out.println(player.getName() + " : "+word);
//            System.out.println(player.getGame().getBag());
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return words.toString();
    }
}
