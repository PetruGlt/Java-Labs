package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadCommand implements Command {
    private Repository repository;
    private ObjectMapper objectMapper;
    private String path;

    public LoadCommand(Repository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(String[] args) {
        path = args[0];
        try {
            List<Image> images = objectMapper.readValue(new File(path), new TypeReference<List<Image>>() {});
            for (Image image : images) {
                repository.addImage(image);
            }
            System.out.println("Images loaded from " + path);
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }
}