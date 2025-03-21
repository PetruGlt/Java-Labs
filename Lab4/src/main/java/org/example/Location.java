package org.example;

public class Location implements Comparable<Location> {
    enum Type {
        FRIENDLY,
        NEUTRAL,
        ENEMY
    }

    private String name;
    private Type type;

    public Location(String name, Type type) {
        this.name = name;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
