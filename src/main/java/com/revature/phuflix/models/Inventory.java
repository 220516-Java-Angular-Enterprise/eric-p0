package com.revature.phuflix.models;

public class Inventory {
    private String movie_id;
    private String phubox_id;
    private int qty;

    public Inventory(String movie_id, String phubox_id, int qty) {
        this.movie_id = movie_id;
        this.phubox_id = phubox_id;
        this.qty = qty;
    }

    public Inventory(){
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
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
}
