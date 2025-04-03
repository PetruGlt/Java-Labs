package com.persistance;

import com.example.Repository;

import java.io.IOException;

public interface Persistance {
    void save(Repository repository, String[] location) throws IOException;
    void load(String[] location) throws IOException, ClassNotFoundException;
}
