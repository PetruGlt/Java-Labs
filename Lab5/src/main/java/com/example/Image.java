package com.example;

import java.io.Serializable;
import java.time.LocalDate;

public record Image(String name, LocalDate dateCreated , String path){
    public Image(String name, String path) {
        this(name, LocalDate.now(), path);
    }
}
