package com.example.carfaultdetector.model;

public class Workshop {
    private String name;
    private String address;
    private double rate;

    public Workshop(){

    }

    public Workshop(String name, String address, double rate){
        this.name = name;
        this.address = address;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
