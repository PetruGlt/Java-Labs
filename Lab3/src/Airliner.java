public class Airliner extends Aircraft implements PassengerCapable{
    int passengerCapacity;
    int wingSpan;

    public Airliner(String name, String model, String tailNumber, int passengerCapacity, int wingSpan) {
        super(name, model, tailNumber);
        this.passengerCapacity = passengerCapacity;
        this.wingSpan = wingSpan;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

}
