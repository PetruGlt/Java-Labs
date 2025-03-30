package com.example;

import java.awt.*;
import java.io.*;
import java.nio.file.InvalidPathException;

import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public void save(Repository repo, String path)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue( new File(path),repo );
    }
    public Repository load(String path)
            throws InvalidCatalogException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Repository repo = objectMapper.readValue(
                new File(path),
                Repository.class);
        return repo;
    }
}
