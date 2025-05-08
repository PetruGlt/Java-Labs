package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        Game game = new Game(dictionary);

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Enter the NUMBER of players: ");
        int players = scanner2.nextInt();

        for(int i=1;i<=players;i++){
            System.out.println("Enter NAME for player " + i+": ");
            String name = scanner.nextLine();
            game.addPlayer(name, scanner);
        }

        long timeLimit = 1 * 60 * 1000; // TIME LIMIT = min * sec * 1sec(1000ms)
        game.startGame(timeLimit);
    }
}


