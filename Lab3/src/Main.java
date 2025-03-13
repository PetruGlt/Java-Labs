import java.util.*;


public class Main {
    public static void main(String[] args) {
        Aircraft[] aircrafts = {
                new Airliner("Avion1", "Public","IS123",200,1050),
                new Airliner("Avion2", "Public","IS123",200,1050),
                new Freighter("Cargo","plane", "B123",5000),
                new Freighter("Cargo2","plane", "B123",5000),
                new Freighter("Cargo3","plane", "B123",5000),
                new Drone("Drone1","plane", "B123",48),
                new Drone("Drone2","plane", "B123",48),
        };

        System.out.println("Cargo-capable aircraft:");
        for (Aircraft a : aircrafts)
            if(a instanceof CargoCapable)
                System.out.println(a + " MaxPayload: " + ((CargoCapable) a).getMaxPayload());

    }
}