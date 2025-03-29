package com.example;

import java.awt.*;
import java.io.*;
import java.nio.file.InvalidPathException;

public class RepositoryService {

    public void view(Image image) {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(image.path());
        if(!file.exists()) {
            System.err.println("File not found: "+ image.path());
            return;
        }

        try{
            desktop.open(file);
        }
        catch(IOException e) {
            System.err.println("Can't open specified file: "+ image.path());

        }

    }
}
