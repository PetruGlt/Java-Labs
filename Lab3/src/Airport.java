public class Airport {
    private final String name;
    private int runways;

    public Airport(String name, int runways) {
        this.name = name;
        this.runways = runways;
    }

    public String getName() {
        return name;
    }

    public int getRunways() {
        return runways;
    }

    public void updateRunways(int runways) {
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
