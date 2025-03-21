import java.time.LocalTime;
import java.util.*;

public class Schedule {

    private final Airport airport;
    private final Set<Flight> flights;
    private final int runways;
    private final Map<Flight, Integer> flightRunwayMap;

    public Schedule(Airport airport, Set<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
        this.runways = airport.getRunways();
        this.flightRunwayMap = new HashMap<>();
    }

    public void assignFlightsOnRunways() {
        List<Flight> sortedFlights = new ArrayList<>(this.flights);
        sortedFlights.sort(Comparator.comparing(flight -> flight.getLandingTimeInterval().getFirst()));

        Queue<Integer> availableRunways = new PriorityQueue<>();
        for (int i = 1; i <= this.runways; i++) {
            availableRunways.add(i);
        }

        Map<Integer, LocalTime> runwayEndTime = new HashMap<>();
        for (int i = 1; i <= this.runways; i++) {
            runwayEndTime.put(i, LocalTime.MIN);
        }

        for (Flight flight : sortedFlights) {
            LocalTime startTime = flight.getLandingTimeInterval().getFirst();
            LocalTime endTime = flight.getLandingTimeInterval().getSecond();

            for (int runway : runwayEndTime.keySet()) {
                if (runwayEndTime.get(runway).isBefore(startTime)) {
                    this.flightRunwayMap.put(flight, runway);
                    runwayEndTime.put(runway, endTime);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder schedule = new StringBuilder("Flight Schedule for " + this.airport.getName()+": \n");
        for (Map.Entry<Flight, Integer> entry : this.flightRunwayMap.entrySet()) {
            schedule.append("Flight ")
                    .append(entry.getKey().getIdentificationNumber())
                    .append(" assigned to Runway ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return schedule.toString();
    }
}



