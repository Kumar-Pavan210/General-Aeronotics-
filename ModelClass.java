package com.example.generalaeronotics;

public class ModelClass {
    int ImageView;
    String name;
    String address;

    ModelClass(int ImageView,String name,String address){
        this.ImageView=ImageView;
        this.name=name;
        this.address=address;
    }

    public int getImageView() {
        return ImageView;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
