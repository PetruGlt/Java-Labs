package com.example;

import java.util.Arrays;
import java.util.Scanner;

public class Shellv2 {
    private Repository repository;
    private boolean running;
    Scanner scanner = new Scanner(System.in);

    public Shellv2(Repository repository) {
        this.repository = repository;
        this.running = true;
        runShell();
    }

    private void runShell(){
        while(running){
            System.out.println("@user: ~/"+repository.getRepoName()+"&> ");
            String userInput = scanner.nextLine();
            cmdHandler(userInput);
        }
        System.out.println("Shutting down...");

    }

    private void cmdHandler(String userInput){
        String[] commands = userInput.split(" ");
        String command = commands[0];
        String[] args = new String[commands.length - 1];
        System.arraycopy(commands, 1, args, 0, args.length);

        for(String arg : args)
            System.out.println(arg);
        switch(command){
            case "add":
                // Usage : add <name> <path> <tags>
                if(commands.length < 2){
                    System.err.println("Usage: add <name> <path> ?<tags>");
                }else{
                    Add addCommand = new Add(repository);
                    addCommand.execute(args);
                }
                break;
            case "remove":
                // Usage: remove <name>
                if(commands.length == 1){
                    System.err.println("Usage: remove <name>");
                }else{
                    Remove removeCommand = new Remove(repository);
                    removeCommand.execute(args);
                }
                break;
                case "list":
                    System.out.println(repository);
                    break;

        }
    }


}
