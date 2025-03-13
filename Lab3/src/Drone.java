public class Drone extends Aircraft{
    private int batteryLife;

    public Drone(String name, String model, String tailNumber, int batteryLife) {
        super(name, model, tailNumber);
        this.batteryLife = batteryLife;
    }

    public int getBatteryLife() {
        return batteryLife;
    }
}
