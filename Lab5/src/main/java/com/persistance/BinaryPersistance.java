package com.persistance;

import com.example.Repository;

import java.io.*;

public class BinaryPersistance implements Serializable, Persistance {
    private Repository repository;
    private String path;

    @Override
    public void save(Repository repository, String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: bin-save <file-path>");
        }

        String path = args[0];

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(repository);
            System.out.println("Repository saved successfully at " + path);
        } catch (IOException e) {
            throw new IOException("Failed to save repository to file: " + path, e);
        }
    }


    @Override
    public void load(String[] args) throws IOException {
        path = args[0];

        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("File not found: " + path);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            this.repository = (Repository) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load repository: Class not found", e);
        }
    }

}
