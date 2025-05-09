package org.example;

public class Country {
    private int id;
    private String name;
    private String code;
    private Continent continent;
    public Country(int id, String name, String code, Continent continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public Continent getContinent() {
        return continent;
    }
    @Override
    public String toString() {
        return "Country [id=" + id + ", name=" + name + ", code=" + code + ", continent="+continent+"]";
    }
}
