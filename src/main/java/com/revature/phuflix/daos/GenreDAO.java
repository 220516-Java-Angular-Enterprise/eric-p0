package com.revature.phuflix.daos;

import com.revature.phuflix.models.Genre;
import com.revature.phuflix.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class GenreDAO implements CrudDAO<Genre> {

    // location of database
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Genre obj) {

    }

    @Override
    public void update(Genre obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Genre getById(String id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }
}
