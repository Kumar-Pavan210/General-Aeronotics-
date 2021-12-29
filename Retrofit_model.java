package com.example.generalaeronotics;

public class Retrofit_model {
    String name;
    String address;
    int id;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public Retrofit_model(String name, String address, int id) {
        this.name = name;
        this.address = address;
        this.id=id;
    }
}
