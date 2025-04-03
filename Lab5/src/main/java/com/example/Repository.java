package com.example;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.clique.MaximalCliqueIterator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

public class Repository implements Serializable {
    private String name;
    private List<Image> images= new ArrayList<> ();
    private List<String> predefinedTags = List.of("funny", "popular", "important", "colorful");

    public Repository(String name) {
        this.name = name;
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public void removeImage(Image image) {
        if(images.contains(image)) {
            images.remove(image);
        }
    }

    public List<Image> getImages() {
        return images;
    }


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
            sb.append(image.name()).append(" - ").append(image.path()).append(" - ").append(image.tags()).append("\n");
        }
        return sb.toString();
    }


    public void addAll(String directoryPath) throws IOException {
        Files.walk(new File(directoryPath).toPath())
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".png"))
                .forEach(path -> {
                    String location = path.toString();
                    String name = path.getFileName().toString();
                    LocalDate date = LocalDate.now(); // set date appropriately
                    List<String> tags = getRandomTags();
                    addImage(new Image(name, date, tags, location));
                });
    }

    private List<String> getRandomTags() {
        Random random = new Random();
        int numberOfTags = random.nextInt(predefinedTags.size()) + 1;
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < numberOfTags; i++) {
            tags.add(predefinedTags.get(random.nextInt(predefinedTags.size())));
        }
        return tags;
    }

    public List<Set<Image>> findMaximalGroups() {
        Graph graph = buildGraph();
        var alg = MaximalCliqueIterator.getInstance(graph);
        List<Set<Image>> groups = new ArrayList<>();
        while (alg.hasNext()) {
            var clique = alg.next();
            Set<Image> group = new HashSet<>();
            for (int vertex : clique) {
                group.add(images.get(vertex));
            }
            groups.add(group);
        }
        return groups;
    }


    private Graph buildGraph() {
        int numImages = images.size();
        Graph graph = GraphBuilder.empty()
                .estimatedNumVertices(numImages)
                .buildGraph();

        for (int i = 0; i < numImages; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < numImages; i++) {
            for (int j = i + 1; j < numImages; j++) {
                if (hasCommonTag(images.get(i), images.get(j))) {
                    graph.addEdge(i, j);
                }
            }
        }
        return graph;
    }
    public void printMaximalGroups() {
        List<Set<Image>> groups = findMaximalGroups();
        for (Set<Image> group : groups) {
            System.out.println("Group:");
            for (Image image : group) {
                System.out.println(" - " + image.name() + " [" + String.join(", ", image.tags()) + "]");
            }
        }
    }

    private boolean hasCommonTag(Image img1, Image img2) {
        for (String tag : img1.tags()) {
            if (img2.tags().contains(tag)) {
                return true;
            }
        }
        return false;
    }
}

