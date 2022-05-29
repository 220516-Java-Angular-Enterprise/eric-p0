package com.revature.phuflix.models;

public class Movies {
    private String id;
    private String  movie_name;
    private int  price;

    public Movies(String id, String movie_name, int price) {
        this.id = id;
        this.movie_name = movie_name;
        this.price = price;
    }

    public Movies(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
