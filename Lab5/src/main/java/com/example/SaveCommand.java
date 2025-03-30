package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaveCommand implements Command {
    private Repository repository;
    private ObjectMapper objectMapper;
    private String path;

    public SaveCommand(Repository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(String[] args) {
        path = args[0];
        List<Image> images = repository.getImages();

        try {
            objectMapper.writeValue(new File(path), images);
            System.out.println("Images saved to " + path);
        } catch (IOException e) {
            System.out.println("Error saving images: " + e.getMessage());
        }
    }
}