package com.revature.phuflix.models;

import java.sql.Timestamp;

public class Orders {
    private String id;
    private String user_id;
    private String phubox_id;
    private String movie_id;
    private int qty;
    // populates when order excuted
    private Timestamp timestamp;

    public Orders(String id, String user_id, String phubox_id, String movie_id, int qty) {
        this.id = id;
        this.user_id = user_id;
        this.phubox_id = phubox_id;
        this.movie_id = movie_id;
        this.qty = qty;
    }

    public Orders(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhubox_id() {
        return phubox_id;
    }

    public void setPhubox_id(String phubox_id) {
        this.phubox_id = phubox_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }
}
