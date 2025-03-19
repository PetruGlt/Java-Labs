import java.util.*;

public class Airport {
    private String name;
    private int runways;

    public Airport(String name, int runways) {
        this.name = name;
        this.runways = runways;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRunways() {
        return runways;
    }

    public void setRunways(int runways) {
        this.runways = runways;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", runways=" + runways +
                '}';
    }
}
