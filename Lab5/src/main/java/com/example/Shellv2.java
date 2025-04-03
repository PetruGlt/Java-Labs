package com.example;


import com.persistance.BinaryPersistance;
import com.persistance.JSONPersistance;
import com.persistance.TextPersistance;

import java.util.Scanner;

import java.awt.*;
import java.io.*;

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
            case "addAll":
                try {
                    repository.addAll(args[0]);
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            case "remove":
                // Usage: remove <name>
                if(commands.length == 1){
                    System.err.println("Usage: remove <name>");
                }else{
                    Remove removeCommand = new Remove(repository);
                    removeCommand.execute(args);
                }
                break;
            case "update":
                // usage: update <name> ?<tags>
                if(commands.length < 3){
                    System.err.println("Usage: update <name> <newname> ?<tags>");
                }
                else{
                    Update update = new Update(repository);
                    update.execute(args);
                }
                break;
            case "view":
                Image image = repository.findImageByName(args[0]);
                
                File file = new File(image.path());
                

                Desktop desktop = Desktop.getDesktop();

                try{
                    desktop.open(file);
                }
                catch(IOException e){
                    System.err.println("Could not open file " + file.getAbsolutePath());
                }
                break;
//            case "save":
//                if (args.length > 1) {
//                    System.err.println("Usage: text <path>");
//                }
//                else {
//                    SaveCommand save = new SaveCommand(repository);
//                    save.execute(args);
//                }
//                break;

            case "bin-save":
                if (args.length < 1) { // FIXED: Corrected argument check
                    System.err.println("Usage: bin-save <path>");
                } else {
                    BinaryPersistance bin = new BinaryPersistance();
                    try {
                        bin.save(repository, args);
                    } catch (IOException e) {
                        System.err.println("Could not save file: " + args[0]); // FIXED: Space added in message
                    }
                }
                break;
            case "bin-load":
                    if (args.length > 1){
                        System.err.println("Usage: bin-load <path>");
                    }
                    else {
                        BinaryPersistance bin = new BinaryPersistance();
                        try {
                            bin.load(args);
                        } catch (IOException e ) {
                            System.err.println("Could not load file " + args[0]);
                        }

                    }
                    break;
            case "text-save":
                if (args.length > 1) {
                    System.err.println("Usage: text-save <path>");
                }
                else {
                    TextPersistance text =  new TextPersistance();
                    try {
                        text.save(repository, args);
                    }
                    catch(IOException e){
                        System.err.println("Could not save file " + args[0]);
                    }
                }
                break;
            case "text-load":
                if(args.length > 1){
                    System.err.println("Usage: text-load <path>");
                }
                else {
                    TextPersistance text =  new TextPersistance();
                    try {
                        text.load(args);
                    } catch (IOException e) {
                        System.err.println("Could not load file " + args[0]);
                    }
                }
                break;
            case "json-load":
//                switch (args[0]){
//                    case "load":
                if (args.length > 1) {
                    System.err.println("Usage: json-load <file-path>");
                }
                JSONPersistance json = new JSONPersistance(repository);
                try {
                    json.load(args);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "json-save":
                if (args.length > 1) {
                    System.err.println("Usage: json-save <file-path>");
                }

                JSONPersistance save = new JSONPersistance(repository);
                save.save(repository, args);
                break;
//            case "load":
//                if (args.length > 1) {
//                    System.err.println("Usage: load <file-path>");
//                }
//                else {
//                    LoadCommand load = new LoadCommand(repository);
//                    load.execute(args);
//                }
//                break;
            case "list":
                System.out.println(repository);
                break;
            case "report":
                if (args.length > 1) {
                    System.err.println("Usage: report [output-path]");
                } else {
                    var reportCommand = new ReportCommand();
                    reportCommand.execute(repository,"/home/petru10/Pictures/in.html");
                }
                break;
            case "max":
                repository.findMaximalGroups();
                repository.printMaximalGroups();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);

       }
    }


}
