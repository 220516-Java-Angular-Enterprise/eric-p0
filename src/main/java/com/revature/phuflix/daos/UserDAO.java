package com.revature.phuflix.daos;

import com.revature.phuflix.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    // location of database
    final String path = "src/main/resources/database/user.txt";
    @Override
    public void save(User obj) {
        try{
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            fw.write(obj.toFileString());
            fw.close();
        } catch (IOException e){
            throw new RuntimeException("File not found");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        // get all Users


        return null;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String userData = null; //
            while ((userData = br.readLine()) != null) {
                String[] userArr = userData.split(",");
                usernames.add(userArr[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found.");
        }
        return usernames;
    }

    public User getUserByUsernamePassword(String username, String password) {
        User user  = new User();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String userData = null; //
            while ((userData = br.readLine()) != null) {
                String[] userArr = userData.split(",");
                if (userArr[1].equals(username)){
                    if(userArr[2].equals(password)){
                        user.setId(userArr[0]);
                        user.setUsername(userArr[1]);
                        user.setPassword(userArr[2]);
                        user.setRole(userArr[3]);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found.");
        }
        return user;
    }
}
