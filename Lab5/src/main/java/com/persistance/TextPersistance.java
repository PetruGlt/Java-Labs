package com.persistance;

import com.example.Image;
import com.example.Repository;

import java.io.*;

public class TextPersistance implements Persistance {
    private Repository repository;
    private String path;

    @Override
    public void save(Repository repository, String[] location) throws IOException {
        String path = location[0];
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(String.format("Repository: %s  %n%n", repository.getRepoName()));
            for( Image img : repository.getImages()){
                writer.write(String.format("%s ; %s ; %s ; %s %n", img.name(), img.dateCreated(), String.join(",", img.tags()), img.path()));
            }
        }
    }
    @Override
    public void load(String[] args) throws IOException {

    }

}
