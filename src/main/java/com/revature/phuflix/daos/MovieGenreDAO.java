package com.revature.phuflix.daos;

import com.revature.phuflix.models.Movies;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class MovieGenreDAO implements CrudDAO<Movies> {
    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Movies obj) {

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
        return null;
    }
}
