import java.time.LocalTime;
import java.util.*;


public class Main {
    public static void main(String[] args){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=- Compulsory -=-=-=-=-=-=-=-=-=-=-=-=-\n");
        Main.compulsory();
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=- Homework -=-=-=-=-=-=-=-=-=-=-=-=-\n");
        Main.homework();

    }


    static void compulsory() {
        Aircraft[] aircrafts = {
                new Airliner("Boeing Travel", "Public","IS123",200,1050),
                new Airliner("Boeing 700", "Public","IS123",200,1050),
                new Freighter("Cargo","plane", "B123",5000),
                new Freighter("Cargo2","plane", "B123",5000),
                new Freighter("Cargo3","plane", "B123",5000),
                new Drone("DJI Drone Originals","plane", "B123",48),
                new Drone("DJI Drone Phantom","plane", "B123",48),
        };

        System.out.println("Cargo-capable aircraft:");
        for (Aircraft a : aircrafts)
            if(a instanceof CargoCapable)
                System.out.println(a + " MaxPayload: " + ((CargoCapable) a).getMaxPayload());

    }

    static void homework(){

        Airport airport = new Airport("Henri Coanda Bucharest (OTP)", 5);

        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(new Airliner("Boeing Travel", "Public","IS153",200,1050));
        aircrafts.add(new Airliner("Boeing 700", "Public","IS2523",200,1050));
        aircrafts.add(new Freighter("Cargo","plane", "B133",5000));
        aircrafts.add(new Freighter("Cargo2","plane", "B123",5000));
        aircrafts.add(new Freighter("Cargo3","plane", "B923",5000));
        aircrafts.add(new Drone("DJI Drone Originals","drone", "B1209",48));
        aircrafts.add(new Drone("DJI Drone Phantom","drone", "B1343",48));

        HashSet<Flight> flights = new HashSet<>();
        flights.add(new Flight("FL001", new TimeInterval(LocalTime.of(10,30), LocalTime.of(11, 10))));
        flights.add(new Flight("FL012", new TimeInterval(LocalTime.of(10,50), LocalTime.of(11, 30))));
        flights.add(new Flight("FL032", new TimeInterval(LocalTime.of(11,15), LocalTime.of(11, 50))));
        flights.add(new Flight("FL005", new TimeInterval(LocalTime.of(11,0), LocalTime.of(11, 40))));
        flights.add(new Flight("FL011", new TimeInterval(LocalTime.of(10,30), LocalTime.of(11, 10))));

        System.out.println("Flights: \n");
        for(Flight f : flights){
            System.out.println(f);
        }

        System.out.println(" \nSolution: \n");

        Schedule problem = new Schedule(airport, flights);
        problem.assignFlightsOnRunways();
        System.out.println(problem);

    }
}