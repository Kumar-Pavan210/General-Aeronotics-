package com.example.generalaeronotics;

public class NestedAdrress {
    private String street;
    private String suite;
    private String city;

    public NestedAdrress(String street, String suite, String city, NestedLong geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.geo = geo;
    }

    private NestedLong geo;

    public NestedLong getGeo() {
        return geo;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }
}
