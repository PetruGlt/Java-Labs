public class Flight {
    private final String identificationNumber;
    private final TimeInterval landingTimeInterval;

    public Flight(String identificationNumber, TimeInterval landingTimeInterval) {
        this.identificationNumber = identificationNumber;
        this.landingTimeInterval = landingTimeInterval;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public TimeInterval getLandingTimeInterval() {
        return landingTimeInterval;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "identificationNumber='" + identificationNumber + '\'' +
                ", landingTimeInterval=" + landingTimeInterval +
                '}';
    }
}
