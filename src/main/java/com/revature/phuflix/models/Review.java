package com.revature.phuflix.models;

import java.sql.Timestamp;

public class Review {
    private String  id;
    private String  movie_id;
    private String user_id;
    private int rating;
    private String msg;
    private Timestamp timestamp;

    public Review(String id, String movie_id, String user_id, int rating, String msg, Timestamp timestamp) {
        this.id = id;
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.rating = rating;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public Review(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
