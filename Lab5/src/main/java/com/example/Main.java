package com.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create a new repository: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

//        Path path = Paths.get(input);
//        if(!Files.exists(path)) {
//            try {
//                Path newDir = Files.createDirectory(Path.of(input));
//            }
//            catch (IOException ex) {
//                System.err.println("Could not create directory");
//            }
//        }

        var repository = new Repository(input);
        Image img = new Image("Java-Logo", "/home/petru10/IdeaProjects/Java-Labs/Lab5/resources/lava-logo.png");
        repository.addImage(img);
        var service = new RepositoryService();

//        service.view(img);

        System.out.println(repository);
        try {
            var shell = new Shellv2(repository);
        }
        catch (Exception e) {
            System.err.println("Unknown command");
            var shell = new Shellv2(repository);
        }

    }

}