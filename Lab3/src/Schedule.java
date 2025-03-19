import java.util.*;

public class Schedule {

    private Airport airport;
    private Set<Flight> flights;
    private int runways;
    private Map<Flight, Integer> flightRunwayMap;

    public Schedule(Airport airport, Set<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
        this.runways = airport.getRunways();
        this.flightRunwayMap = new HashMap<>();
    }


}
