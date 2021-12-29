package com.example.generalaeronotics;

public class Model_class {
    private int id;
    private String name;
    private NestedAdrress address;




    public Model_class(int id, String name, NestedAdrress address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }



    public NestedAdrress getAddress() {
        return address;
    }



    public String getName() {
        return name;
    }



    public int getId() {
        return id;
    }

}
