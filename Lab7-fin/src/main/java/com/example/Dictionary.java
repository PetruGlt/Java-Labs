package com.example;

import java.io.*;
import java.util.*;

public class Dictionary {
    private final Set<String> words = new HashSet<>();

    public Dictionary() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dictionary.txt")) {
            if (input == null) {
                throw new FileNotFoundException("dictionary.txt not found in resources");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line.trim().toUpperCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load dictionary: " + e.getMessage());
        }
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }
}
