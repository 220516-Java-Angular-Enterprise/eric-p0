package com.revature.phuflix.models;

public class Phubox {
    private String id;
    private String city;
    private String state;

    public Phubox(String id, String city, String state) {
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
