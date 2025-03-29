package com.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create a new repository: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Path path = Paths.get(input);
        if(!Files.exists(path)) {
            try {
                Path newDir = Files.createDirectory(Path.of(input));
            }
            catch (IOException ex) {
                System.err.println("Could not create directory");
            }
        }

        var repository = new Repository(input);
        Image img = new Image("Java-Logo", "/home/petru10/IdeaProjects/Java-Labs/Lab5/resources/lava-logo.png");
        repository.add(img);
        var service = new RepositoryService();

        service.view(img);

        System.out.println(repository);

    }

}