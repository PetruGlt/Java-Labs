public class Freighter extends Aircraft implements CargoCapable{
    private double maxPayload;

    public Freighter(String name, String model, String tailNumber, int maxPayload) {
        super(name, model, tailNumber);
        this.maxPayload = maxPayload;
    }

    public double getMaxPayload() {
        return maxPayload;
    }
}
