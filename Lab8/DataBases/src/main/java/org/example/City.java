package org.example;

public class City {
    private int id;
    private Country country;
    private String name;
    public boolean capital;
    public double latitude;
    public double longitude;
    public City(int id, Country country, String name, boolean capital, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public int getId() {
        return id;
    }
    public Country getCountry() {
        return country;
    }
    public String getName() {
        return name;
    }
    public boolean isCapital() {
        return capital;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    @Override
    public String toString() {
        return "City [id=" + id + ", country=" + country + ", name=" + name + ", capital=" + capital + ", latitude="+latitude+", longitude="+longitude+"]";
    }
}
