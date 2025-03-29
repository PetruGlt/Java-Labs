package com.example;

import java.util.List;
import java.util.Scanner;

public class Shell {
    private Repository repository;
    private Scanner scanner;
    private boolean quit = true;

    public Shell(Repository repository) {
        this.repository = new Repository(repository.getRepoName());
        this.scanner = new Scanner(System.in);
        start();
    }

    private void start() {

        while (true) {
            System.out.println("&user> ");
            String cmd = scanner.nextLine();
            cmdHandler(cmd);
        }
    }

    private void cmdHandler(String command){
        switch(command){
            case "help":
                System.out.println("Available commands:==========");
                    break;
            case "list":
                System.out.println(repository);
                    break;
            case "add":
                System.out.println("Provide the name of the new item: ");
                String name = scanner.nextLine();
                System.out.println("Provide the path of the new item: ");
                String path = scanner.nextLine();
                System.out.println("Do you want to provide tags for the new item? (Y/N)");
                String option = scanner.nextLine();
                    switch(option){
                        case "y":
                            System.out.println("Provide the tags for the new item (separated by space): ");
                            List<String> tags = List.of(scanner.nextLine().split(" "));
                            repository.addImage(new Image(name, path));
                            System.out.println("Item added to the repository: " + name + tags);
                            break;
                        case "n":
                            repository.addImage(new Image(name, path));
                            System.out.println("Item added to the repository: " + name);
                            break;
                    }
                    break;
            case "open":
                System.out.println("Provide the name of the image you want to open: ");
                String toOpen = scanner.nextLine();
                RepositoryService service = new RepositoryService();
                service.view(repository.findImageByName(toOpen));
                break;
                case "exit":
                    return;

                    default:
                        System.out.println("Invalid command");
                        break;
            }
        }

    }

