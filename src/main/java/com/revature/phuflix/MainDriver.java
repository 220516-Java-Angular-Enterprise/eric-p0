package com.revature.phuflix;

import com.revature.phuflix.daos.UserDAO;
import com.revature.phuflix.services.UserService;
import com.revature.phuflix.ui.StartMenu;
import com.revature.phuflix.util.database.DatabaseConnection;
import com.sun.corba.se.spi.ior.ObjectKey;

public class MainDriver {
    public static void main(String[] args){
        UserDAO userDAO = new UserDAO();
        UserService user = new UserService(userDAO);
        new StartMenu(user).start();
    }

}
