package org.example;


import com.github.javafaker.Faker;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();

        var locations = IntStream.rangeClosed(0, 4)
                .mapToObj(i -> new Location(faker.address().streetAddress(), Math.random()<0.5 ? Location.Type.FRIENDLY : Math.random()<0.5 ? Location.Type.NEUTRAL : Location.Type.ENEMY ) )
                .toArray(Location[]::new);

        for(Location location : locations) {
            System.out.println(location);
        }
        System.out.println("----------------");
        Set<Location> friendlyLocations = Arrays.stream(locations)
                .filter(location -> location.getType() == Location.Type.FRIENDLY)
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(TreeSet::new));

        friendlyLocations.forEach(System.out::println);
        System.out.println("-------------------");

        List<Location> enemyLocations = Arrays.stream(locations)
                .filter(location -> location.getType() == Location.Type.ENEMY)
                .sorted(Comparator.comparing(Location::getName).thenComparing(Location::getType))
                .collect(Collectors.toCollection(LinkedList::new));

        enemyLocations.forEach(System.out::println);
    }


}
