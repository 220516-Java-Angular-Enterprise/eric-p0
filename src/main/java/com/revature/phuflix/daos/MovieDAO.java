package com.revature.phuflix.daos;

import com.revature.phuflix.models.Movies;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.util.custom_exception.UserInputException;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements CrudDAO<Movies> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Movies obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO movies (id, name,price) VALUES (?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getMovie_name());
            ps.setInt(3, obj.getPrice());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("An error occurred when trying to save to the database");
        }

    }

    @Override
    public void update(Movies obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Movies getById(String id) {
        return null;
    }

    @Override
    public List<Movies> getAll() {
        List<Movies> movies = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from movies");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Movies movie = new Movies(rs.getString("id"), rs.getString("name"), rs.getInt("price"));
                movies.add(movie);
            }
        }catch (SQLException e){
            throw new UserInputException("Sql Error");
        }
        return movies;
    }
}
