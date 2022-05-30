package com.revature.phuflix.services;

import com.revature.phuflix.daos.UserDAO;
import com.revature.phuflix.models.User;
import com.revature.phuflix.util.custom_exception.UserInputException;

import javax.jnlp.UnavailableServiceException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void register(User user){
        userDAO.save(user);
    }

    public boolean isUniqueUsername(String username) {
        List<String> usernames = new ArrayList<>();
        usernames = userDAO.getAllUsernames();
        if (usernames.contains(username)) {
            throw new UserInputException("Username Taken");
        } else {
            return true;
        }
    }

    public boolean isValidUsername(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;
        throw new UserInputException("Invalid username. Username needs to be 8-20 characters long.");
    }

    public boolean isValidPassword(String password) {
        if(password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;
        throw new UserInputException("Invalid username. Username needs to be 8-20 characters long.");
    }


    public User login(String username, String password) {
        User user =  userDAO.getUserByUsernamePassword(username,password);
        if (isValidInfo(user)){
            return user;
        }
        throw new RuntimeException("Incorrect info");
    }

    private boolean isValidInfo(User user) {
        if(user.getUsername() == null) throw new UserInputException("Incorrect Username or Password.");
        return true;
    }

    public List<User> getAllUsers(){
        return userDAO.getAll();
    }
}
