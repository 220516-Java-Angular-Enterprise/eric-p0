package com.revature.phuflix.ui;

import com.revature.phuflix.models.User;

public class MainMenu implements IMenu {

    private final User user;

    public MainMenu(User user){
        this.user = user;
    }

    @Override
    public void start() {

        System.out.println("Welcome " + user.getUsername());



    }
}
