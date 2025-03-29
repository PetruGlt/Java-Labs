package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repository implements Serializable {
    private String name;
    private List<Image> images= new ArrayList<> ();

    public Repository(String name) {
        this.name = name;
    }

    public void add(Image image) {
        images.add(image);
    }

    public List<Image> getImages() {
        return images;
    }

    public void addAll(String folder) {}


    public Image findImageByName(String name) {
        for(Image image : images) {
            if (image.name().equals(name)) {
                return image;
            }
        }
        return null;
    }

    public String getRepoName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Repository content:\n");
        for (Image image : images) {
            sb.append(image.name()).append(" - ").append(image.path()).append("\n");
        }
        return sb.toString();
    }

}
