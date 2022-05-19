package com.revature.phuflix;

import com.revature.phuflix.services.UserService;
import com.revature.phuflix.ui.StartMenu;

public class MainDriver {
    public static void main(String[] args){

        UserService user = new UserService();

        new StartMenu(user).start();
    }

}
