package com.example;
import java.util.*;
import java.util.Scanner;


public class Player implements Runnable {
    private final String name;
    private final TileBag tileBag;
    private final Board board;
    private final Dictionary dictionary;
    private final List<Tile> hand = new ArrayList<>();
    private final Game game;
    private int score = 0;
    private final Scanner scanner;

    public Player(String name, TileBag tileBag, Board board, Dictionary dictionary, Game game, Scanner scanner) {
        this.name = name;
        this.tileBag = tileBag;
        this.board = board;
        this.dictionary = dictionary;
        this.game = game;
        this.scanner = scanner;
    }

    public String getNamePlayer(){
        return name;
    }

    public int getScore() {
        return score;
    }

    private void refillHand(int count) {
        hand.addAll(tileBag.extractTiles(count));
    }

    @Override
    public void run() {
        game.registerPlayerThread(Thread.currentThread());
        refillHand(10);

        while (!game.isGameOver()) {
            synchronized (game.getLock()) {
                while (!game.isMyTurn(this)) {
                    try {
                        game.getLock().wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                if (hand.isEmpty()) {
                    refillHand(10);
                }

                if (Thread.currentThread().isInterrupted() || game.isGameOver()) {
                    break;
                }

                System.out.println("\n" + name + "'s turn. Hand: " + hand);
                System.out.print("Enter a word (or 'skip'): ");
                String input;
                try {
                    input = scanner.nextLine().trim().toUpperCase();
                } catch (Exception e) {
                    break;
                }

                if (input.equals("SKIP")) {
                    System.out.println(name + " skipped this round. The tiles were discarded.");
                    hand.clear();
                    refillHand(7);
                } else if (dictionary.isValidWord(input) && canFormWord(input)) {
                    int points = computePoints(input);
                    removeUsedTiles(input);
                    board.submitWord(input, this, points);
                    score += points;
                    refillHand(input.length());
                } else {
                    System.out.println("Invalid word or can't be formed from the tiles available in your hand.");
                }

                game.nextTurn();
                game.getLock().notifyAll();
            }
        }

        System.out.println(name + " finished with score: " + score);
    }

    private boolean canFormWord(String word) {
        Map<Character, Integer> tempHand = new HashMap<>();
        for (Tile tile : hand) {
            tempHand.put(tile.getLetter(), tempHand.getOrDefault(tile.getLetter(), 0) + 1);
        }
        for (char c : word.toCharArray()) {
            if (!tempHand.containsKey(c) || tempHand.get(c) == 0) return false;
            tempHand.put(c, tempHand.get(c) - 1);
        }
        return true;
    }

    private void removeUsedTiles(String word) {
        for (char c : word.toCharArray()) {
            for (Iterator<Tile> it = hand.iterator(); it.hasNext(); ) {
                if (it.next().getLetter() == c) {
                    it.remove();
                    break;
                }
            }
        }
    }

    private int computePoints(String word) {
        int points = 0;
        List<Character> used = new ArrayList<>();
        for (char c : word.toCharArray()) used.add(c);
        for (char c : used) {
            for (Tile t : hand) {
                if (t.getLetter() == c) {
                    points += t.getPoints();
                    break;
                }
            }
        }
        return points;
    }
}
