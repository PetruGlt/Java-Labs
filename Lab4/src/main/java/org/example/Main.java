package org.example;


import com.github.javafaker.Faker;
import org.graph4j.*;

import org.graph4j.GraphBuilder;
import org.graph4j.shortestpath.BidirectionalDijkstra;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();
        int numLocations = 0;

        var locations = IntStream.rangeClosed(0, 7)
                .mapToObj(i -> new Location(faker.address().streetAddress(), Math.random()<0.5 ? LocationType.FRIENDLY : Math.random()<0.5 ? LocationType.NEUTRAL : LocationType.ENEMY ) )
                .toArray(Location[]::new);

        for(Location location : locations) {
            System.out.println(location);
            numLocations++;
        }
        System.out.println("-------Friendly Lcations---------");
        Set<Location> friendlyLocations = Arrays.stream(locations)
                .filter(location -> location.getType() == LocationType.FRIENDLY)
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(TreeSet::new));

        friendlyLocations.forEach(System.out::println);
        System.out.println("---------Enemy Locations----------");

        List<Location> enemyLocations = Arrays.stream(locations)
                .filter(location -> location.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getName).thenComparing(Location::getType))
                .collect(Collectors.toCollection(LinkedList::new));

        enemyLocations.forEach(System.out::println);

        Graph graph = GraphBuilder.empty()
                .estimatedNumVertices(9)
                .buildGraph();

        for(int i = 0; i < numLocations; i++) {
            graph.addLabeledVertex(i,locations[i]);
        }

        graph.addEdge(locations[0],locations[1]);
        graph.addEdge(locations[3],locations[1]);
        graph.addEdge(locations[4],locations[2]);
        graph.addEdge(locations[5],locations[3]);
        graph.addEdge(locations[6],locations[4]);
        graph.addEdge(locations[7],locations[5]);
//      graph.addEdge(locations[],locations[2]);

        var alg = new BidirectionalDijkstra(graph, graph.findVertex(locations[0]), graph.findVertex(locations[7]));
        double length = alg.getPathWeight();
        System.out.println(length);

        LocationByType mapped = new LocationByType();
        mapped.storeLocationsByType(List.of(locations));

        System.out.println(mapped.getLocationsByType());
    }

}