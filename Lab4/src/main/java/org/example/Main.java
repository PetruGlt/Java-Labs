package org.example;


import com.github.javafaker.Faker;
import org.graph4j.*;

import org.graph4j.GraphBuilder;
import org.graph4j.shortestpath.BidirectionalDijkstra;
import org.graph4j.util.Path;

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

        int index =0 ;
        for(Location location : locations) {
            System.out.println(index++ + ". " +  location);
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
        System.out.println("------------------------------------------------------------");
        System.out.println();

        Graph graph = GraphBuilder.empty()
                .estimatedNumVertices(8)
                .buildGraph();

        for(int i = 0; i < numLocations; i++) {
            graph.addLabeledVertex(i,locations[i]);
        }
// v1
//        graph.addEdge(locations[0],locations[1]);
//        graph.addEdge(locations[3],locations[1]);
//        graph.addEdge(locations[4],locations[2]);
//        graph.addEdge(locations[5],locations[3]);
//        graph.addEdge(locations[6],locations[4]);
//        graph.addEdge(locations[7],locations[5]);

//v2
        System.out.println("Random generated graph with the followind egdes: ");
        for(int i = 0; i < numLocations; i++) {
            Random random = new Random();
            for(int j = 0; j < random.nextInt(7); j++){
                Random rand = new Random();
                int finalNode = rand.nextInt(7);
                if(finalNode != i){ //&& i < finalNode) {
                    System.out.println(i + " - " + finalNode);
                    graph.addEdge(locations[i], locations[finalNode]);
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start location: ");
        int startLocationIndex = scanner.nextInt();
        System.out.println("---------Fastest Routes From START to all locations---------");


        Location start = locations[startLocationIndex];
        for( Location loc : locations) {
            if(loc != start){
                var alg = new BidirectionalDijkstra(graph, graph.findVertex(start), graph.findVertex(loc));
                Path path = alg.findPath();
                System.out.println(path +" | "+ start+ "->" + loc +" | Length: "+ alg.getPathWeight());
            }
        }

        LocationByType mapped = new LocationByType();
        mapped.storeLocationsByType(List.of(locations));

        System.out.println(mapped.getLocationsByType());
    }

}