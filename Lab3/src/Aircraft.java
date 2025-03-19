abstract class Aircraft implements Comparable<Aircraft>{
    protected String name;
    protected String model;
    protected String tailNumber;
    protected Flight flight;

    public Aircraft(String name, String model, String tailNumber) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }
    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    @Override
    public int compareTo(Aircraft other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", tailNumber='" + tailNumber + '\'' +
                '}';
    }
}