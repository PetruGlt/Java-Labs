package com.persistance;

import com.example.Image;
import com.example.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONPersistance implements Persistance {
    private Repository repository;
    private ObjectMapper objectMapper;
    private String path;

    public JSONPersistance(Repository repository) {
        this.repository = repository;
    }


    @Override
    public void save(Repository repository, String[] args) {
        path = args[0];
        List<Image> images = repository.getImages();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            objectMapper.writeValue(new File(path), images);
            System.out.println("Images saved to " + path);
        } catch (IOException e) {
            System.out.println("Error saving images: " + e.getMessage());
        }
    }
//    public Repository load(String location) throws IOException, ClassNotFoundException {
//         public LoadCommand(Repository repository) {
//            this.repository = repository;
//            this.objectMapper = new ObjectMapper();
//            this.objectMapper.registerModule(new JavaTimeModule());
//        }

        @Override
        public void load(String[] args) throws IOException {
            path = args[0];
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
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

