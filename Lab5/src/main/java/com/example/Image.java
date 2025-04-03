package com.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record Image(String name, LocalDate dateCreated, List<String> tags, String path) implements Serializable {
    public Image(String name, String path) {
        this(name, LocalDate.now(), new ArrayList<>(), path);
    }

    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    public void setTags(List<String> tags) {
        this.tags.clear();
        this.tags.addAll(tags);
    }




}
