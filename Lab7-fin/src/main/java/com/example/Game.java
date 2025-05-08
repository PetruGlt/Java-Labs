package com.example;

import java.util.*;


public class Game {
    private final TileBag tileBag = new TileBag();
    private final Board board = new Board();
    private final Dictionary dictionary;
    private final List<Player> players = new ArrayList<>();
    private final Object lock = new Object();
    private int currentTurn = 0;
    private boolean gameOver = false;
    private long startTime;
    private final List<Thread> playerThreads = new ArrayList<>();

    public Game(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void addPlayer(String name, Scanner scanner) {
        players.add(new Player(name, tileBag, board, dictionary, this, scanner));
    }

    public void startGame(long timeLimitMillis) {
        startTime = System.currentTimeMillis();
        Thread timer = new Thread(() -> {
            long elapsedTime = 0;
            while (!gameOver && elapsedTime < timeLimitMillis) {
                elapsedTime = System.currentTimeMillis() - startTime;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
            if (!gameOver) {
                System.out.println("\n \uD83D\uDD52 Time limit exceeded. Stopping the game...");
                stopGame();
                interruptPlayers();
            }
        });
        timer.setDaemon(true);
        timer.start();

        List<Thread> threadsToJoin = new ArrayList<>();
        for (Player p : players) {
            Thread playerThread = new Thread(p);
            threadsToJoin.add(playerThread);
        }

        for (Thread t : threadsToJoin){
            t.start();
        }

        for (Thread t : threadsToJoin) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        Player winner = players.stream().max(Comparator.comparingInt(Player::getScore)).orElse(null);
        if (winner != null) {
            System.out.println("\n \uD83C\uDFC6 Winner: " + winner.getNamePlayer() + " with " + winner.getScore() + " points!");
        }
    }

    public Object getLock() {
        return lock;
    }

    public boolean isMyTurn(Player player) {
        return players.get(currentTurn) == player;
    }

    public void nextTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }

    public synchronized void stopGame() {
        gameOver = true;
        synchronized (lock) {
            lock.notifyAll(); // notify all waiting players
        }
    }

    public void registerPlayerThread(Thread t) {
        playerThreads.add(t);
    }

    public void interruptPlayers() {
        for (Thread t : playerThreads) {
            t.interrupt();
        }
    }

    public boolean isGameOver() {
        return gameOver || tileBag.isEmpty();
    }
}

